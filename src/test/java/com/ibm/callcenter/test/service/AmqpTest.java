package com.ibm.callcenter.test.service;

import javax.annotation.Resource;

import com.ibm.callcenter.mq.config.AmqpConfig;
import com.ibm.callcenter.mq.message.MqMessageContent;
import com.ibm.callcenter.mq.service.MessageConsumerService;
import com.ibm.callcenter.mq.service.MessageProductorService;
import com.ibm.callcenter.mq.service.impl.MessageConsumerServiceImpl;
import com.ibm.callcenter.mq.service.impl.MessageProductorServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AmqpConfig.class,MessageConsumerServiceImpl.class,MessageProductorServiceImpl.class})
@TestPropertySource(locations="classpath:test.properties")
public class AmqpTest {

	@Resource
	private MessageProductorService messageProductorService;

	@Resource
	private MessageConsumerService messageConsumer;

	@Test
	public void testMessageQueueUseMsg(){
		try{
			MqMessageContent mqMessageContent=new MqMessageContent();
			mqMessageContent.setBussinessId("1234567");
			mqMessageContent.setCustomerId("itisatest");
			mqMessageContent.setCreateTime(new Date());
			System.out.println("插入数据");
			messageProductorService.convertAndSend("rabbit_queue_default",mqMessageContent);
			System.out.println("读取数据");
			MqMessageContent mqMessageContent1=(MqMessageContent)messageConsumer.receiveAndConvert("rabbit_queue_default");
			System.out.println(mqMessageContent1.getBussinessId());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
