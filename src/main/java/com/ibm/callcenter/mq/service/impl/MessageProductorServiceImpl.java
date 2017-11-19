package com.ibm.callcenter.mq.service.impl;


import com.ibm.callcenter.mq.service.MessageProductorService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProductorServiceImpl implements MessageProductorService{
	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * 向默认Exchange默认routingKey发送消息
	 * @param message 消息体
	 * @return
	 */
	@Override
	public boolean send(Message message){
		try {
			this.amqpTemplate.send(message);
			return true;
		}catch (AmqpException e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 向默认Exchange的指定routingKey发送消息
	 * @param routingKey 路由键（队列名）
	 * @param message 消息体
	 * @return
	 */
	@Override
	public boolean send(String routingKey, Message message){
		try {
			this.amqpTemplate.send(routingKey,message);
			return true;
		}catch (AmqpException e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 向指定Exchange的指定routingKey发送消息
	 * @param exchange Exchange名称
	 * @param routingKey 路由键（队列名）
	 * @param message 消息体
	 * @return
	 */
	@Override
	public boolean send(String exchange, String routingKey, Message message) {
		try {
			this.amqpTemplate.send(exchange,routingKey,message);
			return true;
		}catch (AmqpException e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 向默认exchange的默认routingKey 发送消息（会自行将普通消息转换为Message）
	 * @param obj 消息体
	 * @return
	 */
	@Override
	public boolean convertAndSend(Object obj) {
		try {
			this.amqpTemplate.convertAndSend(obj);
			return true;
		}catch (AmqpException e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 向默认exchange的指定routingKey发送消息（会自行将普通消息转换为Message）
	 * @param routingKey 路由键（队列名）
	 * @param obj 消息体
	 * @return
	 */
	@Override
	public boolean convertAndSend(String routingKey, Object obj) {
		try {
			this.amqpTemplate.convertAndSend(routingKey,obj);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 向指定exchange的指定routingKey发送消息（会自行将普通消息转换为Message）
	 * @param exchange Exchange名称
	 * @param routingKey 路由键（队列名）
	 * @param obj 消息体
	 * @return
	 */
	@Override
	public boolean convertAndSend(String exchange, String routingKey, Object obj) {
		try {
			this.amqpTemplate.convertAndSend(exchange,routingKey,obj);
			return true;
		}catch (AmqpException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean convertAndSend(Object var1, MessagePostProcessor var2) {
		try {
			this.amqpTemplate.convertAndSend(var1,var2);
			return true;
		}catch (AmqpException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean convertAndSend(String var1, Object var2, MessagePostProcessor var3) {
		try {
			this.amqpTemplate.convertAndSend(var1,var2,var3);
			return true;
		}catch (AmqpException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean convertAndSend(String var1, String var2, Object var3, MessagePostProcessor var4) {
		try {
			this.amqpTemplate.convertAndSend(var1,var2,var3,var4);
			return true;
		}catch (AmqpException e){
			e.printStackTrace();
			return false;
		}
	}



	@Override
	public Message sendAndReceive(Message var1) {
		try {
			return this.amqpTemplate.sendAndReceive(var1);
		}catch (AmqpException e){
			return null;
		}
	}

	@Override
	public Message sendAndReceive(String var1, Message var2) {
		try {
			return this.amqpTemplate.sendAndReceive(var1,var2);
		}catch (AmqpException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Message sendAndReceive(String var1, String var2, Message var3) {
		try {
			return this.amqpTemplate.sendAndReceive(var1,var2,var3);
		}catch (AmqpException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object convertSendAndReceive(Object var1) {
		try {
			return this.amqpTemplate.convertSendAndReceive(var1);
		}catch (AmqpException e){
			return null;
		}
	}

	@Override
	public Object convertSendAndReceive(String var1, Object var2) {
		try {
			return this.amqpTemplate.convertSendAndReceive(var1,var2);
		}catch (AmqpException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object convertSendAndReceive(String var1, String var2, Object var3) {
		try {
			return this.amqpTemplate.convertSendAndReceive(var1,var2,var3);
		}catch (AmqpException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object convertSendAndReceive(Object var1, MessagePostProcessor var2) {
		try {
			return this.amqpTemplate.convertSendAndReceive(var1,var2);
		}catch (AmqpException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object convertSendAndReceive(String var1, Object var2, MessagePostProcessor var3) {
		try {
			return this.amqpTemplate.convertSendAndReceive(var1,var2,var3);
		}catch (AmqpException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object convertSendAndReceive(String var1, String var2, Object var3, MessagePostProcessor var4) {
		try {
			return this.amqpTemplate.convertSendAndReceive(var1,var2,var3,var4);
		}catch (AmqpException e){
			e.printStackTrace();
			return null;
		}
	}
}
