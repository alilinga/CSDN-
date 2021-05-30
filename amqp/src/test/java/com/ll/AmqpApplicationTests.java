package com.ll;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AmqpApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Test
	void contextLoads() {
		rabbitTemplate.convertAndSend("ll-directQueue","哈哈哈哈哈");
	}

	// 发消息发一次，两个队列都能收到，因为两个队列都在同一个交换机
	@Test
	public void test1(){
		rabbitTemplate.convertAndSend("fanoutExchange",null,"hello fanoutExchange");
	}

	@Test
	public void test2(){
		rabbitTemplate.convertAndSend("TopicExchange","qa.hhh","这是qa");
//		rabbitTemplate.convertAndSend("TopicExchange","qa.qc","这是qa qc");
	}

	@Test
	public void test3(){
		Message message = MessageBuilder.withBody("hello queueName".getBytes()).setHeader("name", "ll").build();
		rabbitTemplate.send("headersExchange",null,message);
	}

	@Test
	public void test4(){
		Message message = MessageBuilder.withBody("hello queueAge".getBytes()).setHeader("age", 90).build();
		rabbitTemplate.send("headersExchange",null,message);
	}
}
