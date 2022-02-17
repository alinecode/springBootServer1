package com.hello.store.test.service.rabbitMQ.test1xnSimple;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender1xn {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(String context) {
		context = "hello " + context;
		System.out.println("Sender1xn : " + context);
		this.rabbitTemplate.convertAndSend("hello1xn", context);
	}

}