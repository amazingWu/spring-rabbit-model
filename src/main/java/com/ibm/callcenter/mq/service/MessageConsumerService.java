package com.ibm.callcenter.mq.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReceiveAndReplyCallback;
import org.springframework.amqp.core.ReplyToAddressCallback;

public interface MessageConsumerService {
    Message receive();

    Message receive(String var1);

    Object receiveAndConvert();

    Object receiveAndConvert(String var1);

    <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> var1);

    <R, S> boolean receiveAndReply(String var1, ReceiveAndReplyCallback<R, S> var2);

    <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> var1, String var2, String var3);

    <R, S> boolean receiveAndReply(String var1, ReceiveAndReplyCallback<R, S> var2, String var3, String var4);

    <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> var1, ReplyToAddressCallback<S> var2);

    <R, S> boolean receiveAndReply(String var1, ReceiveAndReplyCallback<R, S> var2, ReplyToAddressCallback<S> var3);
}
