package com.ll.config;

import com.ll.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

// 在组件里实现消息的发送和接收
@Component
public class JmsComponent {
    // springboot已经帮我们做了自动化配置，所以在这里面直接注入消息发送模板
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    Queue queue;

    // 发信息
    public void sendMessage(Message message){
        // 发到哪里去queue，发送内容message
        jmsMessagingTemplate.convertAndSend(queue,message);
    }

    // 接收信息
    // JmsListener会时刻监听消息队列，一旦，队列里有消息就会打印出来
    @JmsListener(destination = "ll-Queue") // 确定该消息是接收信息的
    public void receive(Message message){
        System.out.println("msg:" +message);
    }
}
