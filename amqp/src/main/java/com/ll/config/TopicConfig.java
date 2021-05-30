package com.ll.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {
    @Bean
    Queue queuea(){
        return new Queue("qa");
    }
    @Bean
    Queue queueb(){
        return new Queue("qb");
    }
    @Bean
    Queue queuec(){
        return new Queue("qc");
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("TopicExchange",true,false);
    }

    @Bean
    Binding bindinga(){
        // qa开头
        return BindingBuilder.bind(queuea()).to(topicExchange()).with("qa.#");
    }
    @Bean
    Binding bindingb(){
        // qb开头
        return BindingBuilder.bind(queueb()).to(topicExchange()).with("qb.#");
    }
    @Bean
    Binding bindingc(){
        // 只要中间是qc就可以
        return BindingBuilder.bind(queuec()).to(topicExchange()).with("#.qc.#");
    }
}
