package com.ibm.callcenter.test.service;

import javax.annotation.Resource;

import com.ibm.callcenter.mq.config.AmqpConfig;
import com.ibm.callcenter.mq.message.MqMessageContent;
import com.ibm.callcenter.mq.service.MessageConsumerService;
import com.ibm.callcenter.mq.service.MessageProductorService;
import com.ibm.callcenter.mq.service.impl.MessageConsumerServiceImpl;
import com.ibm.callcenter.mq.service.impl.MessageProductorServiceImpl;

import com.ibm.callcenter.mq.utils.Transform;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
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
			mqMessageContent.setBusinessKey("1234567");
			mqMessageContent.setCustomerKey("itisatest");
			mqMessageContent.setCreateTime(new Date());
			Message message=new Message(mqMessageContent.getBytes(),new MessageProperties());
			System.out.println("插入数据");
			messageProductorService.send("rabbit_queue_default",message);
//			System.out.println("读取数据");
//			Message message1=messageConsumer.receive("rabbit_queue_default");
//			System.out.println(((MqMessageContent)Transform.ByteToObject(message1.getBody())).getBusinessKey());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
