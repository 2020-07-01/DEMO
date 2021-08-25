package com.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : RabbitMqPublishController
 * @Author : yq
 * @Date: 2021-08-24
 * @Description :
 */
@RestController
@RequestMapping(value = "/mq")
public class RabbitMqPublishController {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    private void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping(value = "/test1")
    public String test1(String message) {

        Message msg = MessageBuilder.withBody(message.getBytes()).build();
        rabbitTemplate.send("bizExchange", "biz-routing-key", msg);
        return "success!";
    }
}
