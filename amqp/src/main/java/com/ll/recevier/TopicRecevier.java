package com.ll.recevier;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicRecevier {
    @RabbitListener(queues = "qc")
    public void handler3(String message){
        System.out.println("msg3:  "+message);
    }
    @RabbitListener(queues = "qa")
    public void handler1(String message){
        System.out.println("msg1:  "+message);
    }
    @RabbitListener(queues = "qb")
    public void handler2(String message){
        System.out.println("msg2:  "+message);
    }
}
