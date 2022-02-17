package com.hello.store.test.service.rabbitMQ.testNxnSimple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 多对多的发送。结果是，同一个队列，这边发送几次，那边就接收几次
 * 
 * @author AL
 *
 */
@Configuration
public class RabbitConfigNxn {

    @Bean("QueueNxn")
    public Queue queue() {
        return new Queue("helloNxn");
    }

}