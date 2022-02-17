测试使用 rabbitMQ。

〇、准备工作
===

0.1、概览：
spring的简易引导教程（后简称spring引导）： https://spring.io/guides/gs/messaging-rabbitmq/
相关博客内容： http://www.ityouknow.com/springboot/2016/11/30/spring-boot-rabbitMQ.html
			   https://blog.csdn.net/qq_35387940/article/details/100514134
相关api文档引用↓
https://docs.spring.io/spring-amqp/docs/2.4.3-SNAPSHOT/reference/html/#with-spring-boot-auto-configuration-and-an-async-pojo-listener
https://docs.spring.io/spring-amqp/docs/2.4.3-SNAPSHOT/api/

0.2、安装

windows下载rabbitMQ安装程序参考：https://www.cnblogs.com/saryli/p/9729591.html
Linux安装程序 ： 上面的官方引导有。

0.3、准备一个springboot演示程序
可以使用旧程序(boot版本2.0以上)或者从 https://start.spring.io/ 生成（生成方式在上方spring引导有）。

一、代码中简单引用测试
===

1、pom.xml中加入：

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-amqp</artifactId>
    </dependency>

2、配置文件中，加入mq的地址和账号密码，比如：

	# RabbitMQ 配置
	# RabbitMQ 服务器地址
	spring.rabbitmq.host=127.0.0.1
	# RabbitMQ 服务器端口
	spring.rabbitmq.port=5672
	# RabbitMQ 用户名
	spring.rabbitmq.username=guest
	# RabbitMQ 密码
	spring.rabbitmq.password=guest

2、新建一个配置类，注入一个队列的Bean。比如如下代码：↓。关键代码：new Queue("hello")。这是一个重载的构造方法，可以指定是否持久化（默认为是）、是否只是当前连接使用（断开就删除，默认为否）、是否自动删除（就是久了没用就自动删了，默认为否），后面的new交换机也一样有这些部分内容。
/src/main/java/com/hello/store/test/service/rabbitMQ/test1x1Simple/RabbitConfig.java

3、新建一个消息发送者代码，比如：↓。关键代码：rabbitTemplate.convertAndSend("hello", context); 其中的hello和配置类关键代码中的hello联系。
/src/main/java/com/hello/store/test/service/rabbitMQ/test1x1Simple/HelloSender.java

4、新建一个接收者代码，比如：↓。关键代码：@RabbitListener(queues = "hello")，用于指定配置类中的hello队列，如果找不到将启动失败。关键代码@RabbitHandler用于指定处理数据的方法。
/src/main/java/com/hello/store/test/service/rabbitMQ/test1x1Simple/HelloReceiver.java

5、测试 ↓。就是调用一次发送者。
/src/test/java/springbootTest/test/rabbitMQ/RabbitMqHelloTest.java
RabbitMqHelloTest.hello1x1()方法，使用Junit启动。启动正常可以就看到打印出来的发送的消息。

二、一对多、多对多发送的情况
===

代码和上面， test1xnSimple、testNxnSimple 包下面的就是相关代码。结果就是一个队列中，总共发送多少次就会总共接收多少次，且顺序不一定。

三、更多使用方式
===

1、convertAndSend可以发送对象，因为它的参数是object，接收者同样以对象来接收，对象需实现序列化接口。
2、topic方式：代码在这个包下面： testTopic 。配置类关键代码：
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }
	
参数名称就是配置类的一个方法名称（@Bean()自己写名称可以自己试一下）。
3、Fanout方式：
和topic方式类似的，关键代码：

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }
    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
    
多个队列都绑定到 fanoutExchange 这个交换机中，当发送消息，这里的每一个的队列都将收到发送的消息。

4、消息发送者的消息确认方式

需要在配置文件中使用
spring.rabbitmq.publisher-confirms=true
spring.rabbitmq.publisher-returns=true

1）、设置发送确认： rabbitTemplate.setConfirmCallback 参数都是直接new
2）、设置返回回馈： rabbitTemplate.setReturnCallback
只要执行发送到交换机就会执行1）；
找到该交换机，没有找到队列，就还会执行2）；
找到交换机，并且找到队列正常推送，就会只执行1）

5、消息接收者的消息确认方式

配置文件中加上：
spring.rabbitmq.listener.direct.acknowledge-mode=MANUAL

待续...........

（如果失败，会自动重放，可以在配置文件中设置重放次数，超过次数就将舍弃消息。）


四、相关概念
===

1、流程概览
交换机和队列绑定，消息发送者（也叫生产者）发送信息到交换机，交换机根据路由键来调度看往哪一个队列中推送信息，消息接收者（也叫消费者）从队列拿消息。
2、交换机
交换机(Exchange)有4种模式来调度，direct是其默认的调度方式（根据key完全匹配调度）。另外还有 Topic（灵活的）、Headers、Fanout（转发到所有队列）
Topic的路由键是一串由小数点分开的字符串，特殊符号星*号表示一个，井#号表示多个。
Headers模式 是一组键值对，自定义匹配规则，有符合条件的键值对就会被投送到对应队列。
Fanout模式是把消息发送到所有绑定到该交换机的全部队列。




