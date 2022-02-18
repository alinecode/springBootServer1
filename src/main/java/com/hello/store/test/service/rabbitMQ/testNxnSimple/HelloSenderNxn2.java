package com.hello.store.test.service.rabbitMQ.testNxnSimple;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSenderNxn2 {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(String context) {
		context = "helloNxn2 " + context;
//		System.out.println("SenderNxn2 : " + context);
		this.rabbitTemplate.convertAndSend("helloNxn", context);
	}

}