package springbootTest.test.rabbitMQ;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hello.store.App;
import com.hello.store.test.service.rabbitMQ.test1x1Simple.HelloSender;
import com.hello.store.test.service.rabbitMQ.test1xnSimple.HelloSender1xn;
import com.hello.store.test.service.rabbitMQ.testNxnSimple.HelloSenderNxn1;
import com.hello.store.test.service.rabbitMQ.testNxnSimple.HelloSenderNxn2;
import com.hello.store.test.service.rabbitMQ.testTopic.HelloSenderTopic;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class RabbitMqHelloTest {

	@Autowired
	private HelloSender helloSender;
	@Autowired
	private HelloSender1xn helloSender1xn;
	@Autowired
	private HelloSenderNxn1 helloSenderNxn1;
	@Autowired
	private HelloSenderNxn2 helloSenderNxn2;
	@Autowired
	private HelloSenderTopic helloSenderTopic;

	@Test
	public void hello1x1() {
		try {
			helloSender.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void hello1xn() {
		try {
			for (int i=0;i<30;i++){
				helloSender1xn.send(String.valueOf(i));
			}
			System.out.println("投递结束；xxxxxxxxxxxxxxx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void helloNxn() {
		try {
			for (int i=0;i<15;i++){
//				helloSender1xn.send(String.valueOf(i)); 
				helloSenderNxn1.send(String.valueOf(i)); 
				helloSenderNxn2.send(String.valueOf(i)); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void helloSenderTopic() {
		try {
			for (int i=0;i<3;i++){
//				helloSender1xn.send(String.valueOf(i)); 
				helloSenderTopic.send1(); 
				helloSenderTopic.send2(); 
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}