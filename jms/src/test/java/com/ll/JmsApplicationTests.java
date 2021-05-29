package com.ll;

import com.ll.config.JmsComponent;
import com.ll.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class JmsApplicationTests {
	@Autowired
	JmsComponent jmsComponent;

	@Test
	void contextLoads() {
		Message message = new Message();
		message.setContent("哈哈哈哈哈哈哈哈哈哈");
		message.setDate(new Date());
		jmsComponent.sendMessage(message);
	}
}
