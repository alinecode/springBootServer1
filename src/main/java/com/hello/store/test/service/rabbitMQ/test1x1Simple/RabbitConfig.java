package com.hello.store.test.service.rabbitMQ.test1x1Simple;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1对1发送。结果是1个发送者和1个接收者对应。
 * @author AL
 *
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue() {
//        return new Queue("hello",false,true, false);
        return new Queue("hello");
    }
    
    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }
    
}