package com.rabbitmq.plan1;

import javafx.beans.binding.ObjectExpression;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName : Cosumeer
 * @Author : yq
 * @Date: 2021-03-08
 * @Description : 消费端
 */
@Component
public class Consumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /*****同步消费*****/
    public void receive(){
        rabbitTemplate.receive();
    }

    public void receive(String queue){
        Message message = rabbitTemplate.receive(queue);
    }


    public void receive(String queue,long timeOutMillis){
        Object object = rabbitTemplate.receiveAndConvert(queue,timeOutMillis);
    }


    /*****异步消费******/






}
