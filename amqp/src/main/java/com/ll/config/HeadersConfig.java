package com.ll.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class HeadersConfig {
    @Bean
    Queue queueAge(){
        return new Queue("queueAge");
    }
    @Bean
    Queue queueName(){
        return new Queue("queueName");
    }
    @Bean
    HeadersExchange headersExchange(){
        return new HeadersExchange("headersExchange",true,false);
    }
    @Bean
    Binding bindingAge(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("age",90);
        // age要90才路由
        return BindingBuilder.bind(queueAge()).to(headersExchange()).whereAny(map).match();
    }
    @Bean
    Binding bindingName(){
        // 只要存在name属性就可以
        return BindingBuilder.bind(queueAge()).to(headersExchange()).where("name").exists();
    }
}
