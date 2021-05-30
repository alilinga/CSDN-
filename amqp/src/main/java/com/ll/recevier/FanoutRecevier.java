package com.ll.recevier;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutRecevier {
    @RabbitListener(queues = "q1")
    public void handler1(String message){
        System.out.println("1  "+message);
    }
    @RabbitListener(queues = "q2")
    public void handler2(String message){
        System.out.println("2  "+message);
    }
}
