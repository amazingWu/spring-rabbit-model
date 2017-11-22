package com.ibm.callcenter.mq.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

public interface MessageProductorService {
    /**
     * 向默认Exchange默认routingKey发送消息
     * @param message 消息体
     * @return
     */
    boolean send(Message message);

    /**
     * 向默认Exchange的指定routingKey发送消息
     * @param routingKey 路由键（队列名）
     * @param message 消息体
     * @return
     */
    boolean send(String routingKey, Message message);

    /**
     * 向指定Exchange的指定routingKey发送消息
     * @param exchange Exchange名称
     * @param routingKey 路由键（队列名）
     * @param message 消息体
     * @return
     */
    boolean send(String exchange, String routingKey, Message message);

    /**
     * 向默认exchange的默认routingKey 发送消息（相应的接收时需要使用receiveAndConvert）
     * @param obj 消息体
     * @return
     */
    boolean convertAndSend(Object obj);

    boolean convertAndSend(String var1, Object var2);

    boolean convertAndSend(String var1, String var2, Object var3);

    boolean convertAndSend(Object var1, MessagePostProcessor var2);

    boolean convertAndSend(String var1, Object var2, MessagePostProcessor var3);

    boolean convertAndSend(String var1, String var2, Object var3, MessagePostProcessor var4);

    Message sendAndReceive(Message var1);

    Message sendAndReceive(String var1, Message var2);

    Message sendAndReceive(String var1, String var2, Message var3);

    Object convertSendAndReceive(Object var1);

    Object convertSendAndReceive(String var1, Object var2);

    Object convertSendAndReceive(String var1, String var2, Object var3);

    Object convertSendAndReceive(Object var1, MessagePostProcessor var2);

    Object convertSendAndReceive(String var1, Object var2, MessagePostProcessor var3);

    Object convertSendAndReceive(String var1, String var2, Object var3, MessagePostProcessor var4);

}
