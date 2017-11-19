package com.ibm.callcenter.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class AmqpConfig {

	@Value("${mq.host}")
	private String mqHost;
	
	@Value("${mq.port}")
	private Integer mqPort;
	
	@Value("${mq.username}")
	private String mqUserName;
	
	@Value("${mq.password}")
	private String mqPassWord;
	
	@Value("${mq.vhost}")
	private String mqVhost;
	
	@Value("${mq.default.routingkey}")
	private String mqDefaultRoutingKey;
	
	@Value("${mq.default.queuename}")
	private String mqDefaultQueueName;
	
	@Bean
	public ConnectionFactory  connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(mqHost);
		connectionFactory.setPort(mqPort);
		if(mqUserName!=null&&!mqUserName.equals("")){
			connectionFactory.setUsername(mqUserName);
		}
		if(mqPassWord!=null&&!mqPassWord.equals("")){
			connectionFactory.setPassword(mqPassWord);
		}
		if(mqVhost!=null&&!mqVhost.equals("")){
			connectionFactory.setVirtualHost(mqVhost);
		}
		return connectionFactory;
	}

	@Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

	/**
	 * 默认Exchange
	 * @return
	 */
	@Bean
	public DirectExchange defaultExchange(){
		return new DirectExchange("mq-exchange",true,false);
	}

	/**
	 * 默认Queue
	 * @return
	 */
	@Bean
    public Queue queue() {
        Queue queue= new Queue(mqDefaultQueueName,true); //队列持久
		return queue;
    }
	/**
     * 将默认消息队列与默认交换机绑定
     * 针对消费者配置
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(mqDefaultRoutingKey);
    }
    
	
	@Bean
	public AmqpTemplate rabbitTemplate(){
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		RetryTemplate retryTemplate = new RetryTemplate();// 设置失败重试策略，
//		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy(); //指数退避策略
//		backOffPolicy.setInitialInterval(500); // 初始休眠500ms
//		backOffPolicy.setMultiplier(10.0); // 乘数
//		backOffPolicy.setMaxInterval(10000); // 最大休眠时间
		FixedBackOffPolicy fixedBackOffPolicy=new FixedBackOffPolicy(); // 固定时间重试策略，默认线程休眠1s
		retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
		template.setRetryTemplate(retryTemplate);
		template.setExchange("mq-exchange");
		template.setRoutingKey(mqDefaultRoutingKey);
		template.setMessageConverter(new Jackson2JsonMessageConverter());//设置默认的消息序列化方法
		return template;
	}
	
	
}
