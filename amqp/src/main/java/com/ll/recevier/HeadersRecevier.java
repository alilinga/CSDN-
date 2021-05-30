package com.ll.recevier;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeadersRecevier {
    @RabbitListener(queues = "queueAge")
    public void handler1(String message){
        System.out.println("age  "+message);
    }
    @RabbitListener(queues = "queueName")
    public void handler2(String message){
        System.out.println("msg  "+message);
    }
}
