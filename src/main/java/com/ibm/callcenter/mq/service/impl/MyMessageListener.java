package com.ibm.callcenter.mq.service.impl;

import com.ibm.callcenter.mq.message.MqMessageContent;
import com.ibm.callcenter.mq.utils.Transform;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MyMessageListener implements MessageListener{
    @Override
    public void onMessage(Message message) {
        System.out.println("监听器");
        MqMessageContent mqMessageContent=(MqMessageContent) Transform.ByteToObject(message.getBody());
        System.out.println(mqMessageContent.getBusinessKey());
    }
}
