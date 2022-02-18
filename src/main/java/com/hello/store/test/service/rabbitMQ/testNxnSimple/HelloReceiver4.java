package com.hello.store.test.service.rabbitMQ.testNxnSimple;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
@RabbitListener(queues = "helloNxn")
public class HelloReceiver4 {

    @RabbitHandler
    public void process(String hello, Message message, Channel channel) {
        System.out.println("Receiver4  : " + hello);
        try {
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
