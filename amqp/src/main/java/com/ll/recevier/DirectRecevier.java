package com.ll.recevier;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
// 消息接收
public class DirectRecevier {
    @RabbitListener(queues = "ll-directQueue")
    public void handlerDirect(String message){
        System.out.println("msg:   "+message);
    }
}
