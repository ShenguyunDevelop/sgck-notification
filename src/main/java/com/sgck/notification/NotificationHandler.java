/**
 * 
 */
package com.sgck.notification;

/**
 * @author yuan
 * 2015-8-26����2:36:57
 */
public interface NotificationHandler {
	public void onNotification(String topic,String tag,byte[] content);
}
