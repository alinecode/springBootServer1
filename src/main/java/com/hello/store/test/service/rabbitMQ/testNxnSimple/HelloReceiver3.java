package com.hello.store.test.service.rabbitMQ.testNxnSimple;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "helloNxn")
public class HelloReceiver3 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver3  : " + hello);
    }

}
