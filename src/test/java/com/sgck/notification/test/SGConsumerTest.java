package com.sgck.notification.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sgck.notification.consumer.SGConsumer;
//import sun.management.ManagementFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:consumer.xml"})
public class SGConsumerTest {
//	public String getPID(){
//		// get name representing the running Java virtual machine.
//		String name = ManagementFactory.getRuntimeMXBean().getName();
//		return name.split("@")[0];
//	}
	
//	@Autowired SGConsumer consumer;
	@Test
	public void test() {
		while(true){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("ThreadId:[" + "] I'am avlive");
		}
	}

}
