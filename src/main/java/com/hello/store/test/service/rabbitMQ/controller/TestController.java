package com.hello.store.test.service.rabbitMQ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hello.store.test.service.rabbitMQ.test1x1Simple.HelloSender;
import com.hello.store.test.service.rabbitMQ.testNxnSimple.HelloSenderNxn1;
import com.hello.store.test.service.rabbitMQ.testNxnSimple.HelloSenderNxn2;
import com.hello.store.test.service.rabbitMQ.testTopic.HelloSenderTopic;

@RequestMapping("/tmq")
@RestController
public class TestController {

	@Autowired
	HelloSender helloSender;
	@Autowired
	HelloSenderNxn1 helloSenderNxn1;
	@Autowired
	HelloSenderNxn2 helloSenderNxn2;
	@Autowired
	HelloSenderTopic helloSenderTopic;
	
	@RequestMapping("/1")
	public void name1() {
		helloSender.send();
	}
	@RequestMapping("/topic")
	public void nametopic() {
		helloSenderTopic.send1();
		helloSenderTopic.send2();
	}
}
