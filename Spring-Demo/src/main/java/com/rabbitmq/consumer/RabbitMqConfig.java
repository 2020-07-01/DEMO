package com.rabbitmq.consumer;

import com.rabbitmq.producter.ListenerMessage;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : RabbitMqConfig
 * @Author : yq
 * @Date: 2020-12-04
 * @Description : 生产者配置
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 连接工厂
     *
     * @return
     */
    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
        cachingConnectionFactory.setChannelCacheSize(10);
        cachingConnectionFactory.setHost("139.196.186.217");
        cachingConnectionFactory.setUsername("user");
        cachingConnectionFactory.setPassword("password");
        cachingConnectionFactory.setPort(5672);
        cachingConnectionFactory.setVirtualHost("/");
        return cachingConnectionFactory;
    }

    /**
     * 声名rabbitTemplate
     * @param cachingConnectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(@Qualifier("cachingConnectionFactory") CachingConnectionFactory cachingConnectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             * 发送确认回调
             * @param correlationData
             * @param ack
             * @param cause
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {

            }
        });

        return rabbitTemplate;
    }

    /****直连交换机****/
    @Bean
    public Queue queue() {
        Queue queue = new Queue("queue-direct");
        return queue;
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with("123");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("exchange-direct");
    }

    @Bean
    public DirectMessageListenerContainer directMessageListenerContainer(@Qualifier("cachingConnectionFactory") CachingConnectionFactory cachingConnectionFactory) {

        DirectMessageListenerContainer directMessageListenerContainer = new DirectMessageListenerContainer(cachingConnectionFactory);
        directMessageListenerContainer.setQueueNames("queue-demo");
        directMessageListenerContainer.setMessageListener(new ListenerMessage());
        directMessageListenerContainer.setExposeListenerChannel(true);
        return directMessageListenerContainer;
    }


}
