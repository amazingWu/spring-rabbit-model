package com.ibm.callcenter.mq.service.impl;

import com.ibm.callcenter.mq.service.MessageConsumerService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerServiceImpl implements MessageConsumerService{
	
	@Autowired
	private AmqpTemplate amqpTemplate;

	@Override
	public Message receive() {
		try {
			return this.amqpTemplate.receive();
		}catch (AmqpException e){
			return null;
		}
	}

	@Override
	public Message receive(String var1) {
		try {
			return this.amqpTemplate.receive(var1);
		}catch (AmqpException e){
			return null;
		}
	}

	@Override
	public Object receiveAndConvert() {
		try {
			return this.amqpTemplate.receiveAndConvert();
		}catch (AmqpException e){
			return null;
		}
	}

	@Override
	public Object receiveAndConvert(String var1) {
		try {
			return this.amqpTemplate.receiveAndConvert(var1);
		}catch (AmqpException e){
			return null;
		}
	}

	@Override
	public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> var1) {
		try {
			return this.amqpTemplate.receiveAndReply(var1);
		}catch (AmqpException e){
			return false;
		}
	}

	@Override
	public <R, S> boolean receiveAndReply(String var1, ReceiveAndReplyCallback<R, S> var2) {
		try {
			return this.amqpTemplate.receiveAndReply(var1,var2);
		}catch (AmqpException e){
			return false;
		}
	}

	@Override
	public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> var1, String var2, String var3) {
		try {
			return this.amqpTemplate.receiveAndReply(var1,var2,var3);
		}catch (AmqpException e){
			return false;
		}
	}

	@Override
	public <R, S> boolean receiveAndReply(String var1, ReceiveAndReplyCallback<R, S> var2, String var3, String var4) {
		try {
			return this.amqpTemplate.receiveAndReply(var1,var2,var3,var4);
		}catch (AmqpException e){
			return false;
		}
	}

	@Override
	public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> var1, ReplyToAddressCallback<S> var2) {
		try {
			return this.amqpTemplate.receiveAndReply(var1,var2);
		}catch (AmqpException e){
			return false;
		}
	}

	@Override
	public <R, S> boolean receiveAndReply(String var1, ReceiveAndReplyCallback<R, S> var2, ReplyToAddressCallback<S> var3) {
		try {
			return this.amqpTemplate.receiveAndReply(var1,var2,var3);
		}catch (AmqpException e){
			return false;
		}
	}
}
