package com.hello.store.test.service.rabbitMQ.test1xnSimple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1对多发送。结果是 通过1个发送者多次发送，会均匀的分配给多个接收者，不确定是哪一个接收者处理。
 * @author AL
 *
 */
@Configuration
public class RabbitConfig1xn {

    @Bean("Queue1xn")
    public Queue Queue() {
        return new Queue("hello1xn");
    }

}