package com.rabbitmq;

import com.rabbitmq.plan1.ConfirmCallbackExtend;
import com.rabbitmq.plan1.ReturnCallbackExtend;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : RabbitMqConfig
 * @Author : yq
 * @Date: 2021-03-08
 * @Description : 保证消息投递到exChange
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public CachingConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("hostName", 5672);
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    /**
     * @return
     */

    @Bean
    public RabbitTemplate rabbitTemplate() {

        RabbitTemplate template = new RabbitTemplate();

        //设置确认
        template.setConfirmCallback(new ConfirmCallbackExtend());

        //当无法找到队列时时消息回退给生产者
        template.setMandatory(true);
        template.setReturnCallback(new ReturnCallbackExtend());

        return template;

    }


    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();

        return cachingConnectionFactory;
    }


    /**
     * 交换机 队列  message
     * 三者同时设置为持久化模式
     */


}


