package com.sgck.notification;

import org.springframework.stereotype.Component;

import com.sgck.notification.NotificationHandler;
import com.sgck.notification.annotation.NotificationCallback;

@Component
@NotificationCallback(topic="loveyou",tag="test")
public class MyNotificationHandler2 implements NotificationHandler {

	@Override
	public void onNotification(String topic, String tag, byte[] content) {
		String msg = new String(content);
		System.out.println("topic is " + topic + ",tag is " + tag + ",content is " + msg);
	}
}
