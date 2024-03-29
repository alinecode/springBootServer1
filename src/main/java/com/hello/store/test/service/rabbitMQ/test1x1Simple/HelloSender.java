package com.hello.store.test.service.rabbitMQ.test1x1Simple;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	public void send() {
		String context = "hello " + new Date();
		System.out.println("Sender : " + context);
//		this.rabbitTemplate.convertAndSend("lonelyDirectExchange","hello666", context);
		this.rabbitTemplate.convertAndSend("","hello", context);
	}

}