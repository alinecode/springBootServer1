package com.hello.store.test.service.rabbitMQ.testTopic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.message")
public class HelloReceiver6 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("topic.message  : " + hello);
    }

}
