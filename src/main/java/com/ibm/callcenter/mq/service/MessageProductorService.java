package com.ibm.callcenter.mq.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

public interface MessageProductorService {
    boolean send(Message var1);

    boolean send(String var1, Message var2);

    boolean send(String var1, String var2, Message var3);

    boolean convertAndSend(Object var1);

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
