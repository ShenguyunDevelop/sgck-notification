package com.sgck.notification.test;


import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sgck.notification.producer.SGProducer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test.xml"})
public class SGProducerTest {

	@Autowired SGProducer producer;
	
	@Test
	public void test() {
		for(int i = 0; i< 50; i++){
			String msg = i + "YC: I love this world";
//			if(!producer.sendNofication("yiocio", "test", msg.getBytes())){
//				System.out.println("Failed to send msg");
//			}
			
			if(!producer.sendNofication("loveyou", "test", msg.getBytes())){
				System.out.println("Failed to send msg");
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("over!");
		
	}

}
