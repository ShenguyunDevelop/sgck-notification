package com.sgck.notification.producer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.sgck.notification.NotificationProducer;

public class SGProducer implements NotificationProducer{

    private final Logger logger = LoggerFactory.getLogger(SGProducer.class);

    private DefaultMQProducer defaultMQProducer;
    private String producerGroup = "sgck-notify-service";
    private String namesrvAddr;
    
    /**
     * Spring bean init-method
     */
    public void init() throws MQClientException {
        defaultMQProducer = new DefaultMQProducer(producerGroup);
        defaultMQProducer.setNamesrvAddr(namesrvAddr);
        defaultMQProducer.setInstanceName(String.valueOf(System.currentTimeMillis()));
        defaultMQProducer.start();
        
        logger.info("Proudcer OK to start");
    }

    /**
     * Spring bean destroy-method
     */
    public void destroy() {
        defaultMQProducer.shutdown();
    }

    public DefaultMQProducer getDefaultMQProducer() {
        return defaultMQProducer;
    }
    
    // ---------------setter -----------------

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

	@Override
	public boolean sendNofication(String topic, String tag, byte[] content) {
		Message msg = new Message(topic, tag, content);
        SendResult sendResult = null;
        try {
            //sendResult = defaultMQProducer.send(msg);
            sendResult = defaultMQProducer.send(msg, new MessageQueueSelector(){
				@Override
				public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
					Integer id = (Integer) arg;
                    return mqs.get(id);
				}}, 0);
        } catch (Exception e) {
            logger.error(e.getMessage() + String.valueOf(sendResult));
            return false;
        } 

        if (sendResult == null || sendResult.getSendStatus() != SendStatus.SEND_OK) {
        	logger.error("failed to send message,SendStatus is not OK");
        	return false;
        }
        
        return true;
	}
}