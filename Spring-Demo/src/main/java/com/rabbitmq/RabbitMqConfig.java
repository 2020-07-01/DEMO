package com.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : RabbitMqConfig
 * @Author : yq
 * @Date: 2020-12-04
 * @Description :
 */
@Configuration
public class RabbitMqConfig {


    /**
     * 自定义cachingConnectionFactory
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
     * 消息监听
     */
    @Bean
    public Queue queue(){
       return new Queue("queue-demo");
    }

    @Bean
    public DirectMessageListenerContainer directMessageListenerContainer(@Qualifier("cachingConnectionFactory") CachingConnectionFactory cachingConnectionFactory){

       DirectMessageListenerContainer directMessageListenerContainer = new DirectMessageListenerContainer(cachingConnectionFactory);
        directMessageListenerContainer.setQueueNames("queue-demo");
        directMessageListenerContainer.setMessageListener(new ListenerMessage());
        directMessageListenerContainer.setExposeListenerChannel(true);
        return directMessageListenerContainer;
    }


}
