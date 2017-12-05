package com.viseo.c360.cv.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestProducerConfig {

    @Autowired
    ConsumerMessageHandler consumerMessageHandler;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("amq.fanout");
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout-queue1", false);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout-queue2", false);
    }

    @Bean
    public Queue fanoutQueue3() {
        return new Queue("fanout-queue3", false);
    }

    @Bean
    public Queue responseCompetence() {
        return new Queue("response-competence", false);
    }

    @Bean
    public Queue responseFormation() {
        return new Queue("response-formation", false);
    }

    @Bean
    public Queue responseCV() {
        return new Queue("response-cv", false);
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    @Bean
    public Binding binding1(FanoutExchange fanoutExchange, Queue fanoutQueue1) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    @Bean
    public Binding binding2(FanoutExchange fanoutExchange, Queue fanoutQueue2) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

    @Bean
    public Binding binding3(FanoutExchange fanoutExchange, Queue fanoutQueue3) {
        return BindingBuilder.bind(fanoutQueue3).to(fanoutExchange);
    }
/*
    @Bean
    public ConsumerMessageHandler consumerMessageHandler(){
        return new ConsumerMessageHandler();
    }
*/
    @Bean
    public SimpleMessageListenerContainer listenerContainer() {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setQueueNames(fanoutQueue3().getName());
        listenerContainer.setConnectionFactory(connectionFactory());
        listenerContainer.setMessageListener(new MessageListenerAdapter(consumerMessageHandler));
        listenerContainer.setAcknowledgeMode(AcknowledgeMode.NONE);
        return listenerContainer;
    }

}