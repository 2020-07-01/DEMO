package com.rabbitmq.consumer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName : PublisherMessage
 * @Author : yq
 * @Date: 2020-12-06
 * @Description :
 */
@Component
public class PublisherMessage {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     */
    public void sendMessage() {
        rabbitTemplate.convertAndSend("exchange-direct","123","hello world");
    }


}
