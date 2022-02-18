package com.hello.store.test.service.rabbitMQ.test1x1Simple;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

//    @RabbitHandler
//    public void process(String hello) {
//        System.out.println("Receiver  : " + hello);
//    }



//  @RabbitListener(queues = "confirm_queue")
  @RabbitHandler
  public void asyncConfirm(String hello, Message message, Channel channel) throws IOException {

      try {
          System.out.println("消费消息：" + hello);
//          int a = 1 / 0;
          channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
          System.out.println("消费消息确认" + message.getMessageProperties().getConsumerQueue() + "，接收到了回调方法");
      } catch (Exception e) {
          //重新回到队列
//          channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//          System.out.println("尝试重发：" + message.getMessageProperties().getConsumerQueue());
          //requeue =true 重回队列，false 丢弃
    	  e.printStackTrace();
          channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
//          channel.basicReject(message.getMessageProperties().getDeliveryTag(), true); // 这里可能会一直消费
          // TODO 该消息已经导致异常，重发无意义，自己实现补偿机制


      }


  }

    
}
