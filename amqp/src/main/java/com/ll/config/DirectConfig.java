package com.ll.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    @Bean
    Queue directQueue(){
        return new Queue("ll-directQueue");
    }

    // 如果用了Direct 模式，可以不使用DirectExchange Bean和Binding Bean。
//    @Bean
//    DirectExchange directExchange(){
//        // durable:重启后是否有效  autoDelete:长期未使用是否自动删除
//        return new DirectExchange("ll-direct",true,false);
//    }
//
//    // 将队列Queue和交换机DirectExchange绑定到一起
//    @Bean
//    Binding binding(){
//        return BindingBuilder.bind(directQueue()).to(directExchange()).with("direct");
//    }
}
