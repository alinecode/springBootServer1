package com.hello.store.test.service.rabbitMQ.testNxnSimple;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSenderNxn1 {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(String context) {
		context = "helloNxn " + context;
//		System.out.println("SenderNxn1 : " + context);
		this.rabbitTemplate.convertAndSend("helloNxn", context);
	}

}