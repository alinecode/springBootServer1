package com.hello.store.test.service.rabbitMQ.testTopic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSenderTopic {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send1() {
		String context = "hi, i am message 1";
		System.out.println("Sender1 : " + context);
		this.rabbitTemplate.convertAndSend("exchange", "topic.message", context);
	}
	
	public void send2() {
		String context = "hi, i am messages 2";
		System.out.println("Sender2 : " + context);
		this.rabbitTemplate.convertAndSend("exchange", "topic.messages", context);
	}
}