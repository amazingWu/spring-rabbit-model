package com.ibm.callcenter.mq.service.impl;

import com.ibm.callcenter.mq.service.QueueAdminService;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class QueueAdminServiceImpl implements QueueAdminService{

    @Autowired
    private ConnectionFactory connectionFactory;

	@Autowired
	private AmqpAdmin amqpAdmin;

	@Autowired
    private DirectExchange defaultExchange;

	@Autowired
    private Queue defaultQueue;



}
