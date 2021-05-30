package com.ll.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    @Bean
    Queue queue1(){
        return new Queue("q1");
    }
    @Bean
    Queue queue2(){
        return new Queue("q2");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange",true,false);
    }

    // 将两个队列与同一个交换机绑定
    @Bean
    Binding binding1(){
        // 不需要设置routing key
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }
    @Bean
    Binding binding2(){
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }
}
