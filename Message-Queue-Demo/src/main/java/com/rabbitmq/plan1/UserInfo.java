package com.rabbitmq.plan1;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @ClassName : UserInfo
 * @Author : yq
 * @Date: 2021-03-13
 * @Description :
 */
public class UserInfo {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendUserInfo(Object object) {

        String messageId = UUID.randomUUID().toString();
        Message message = MessageBuilderDefine.buildMessage(object, messageId);

        rabbitTemplate.send(message);
    }
}
