package com.rabbitmq.plan1;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName : Producter
 * @Author : yq
 * @Date: 2021-03-08
 * @Description :
 */
public class Producter {

    @Autowired
    private BatchingRabbitTemplate rabbitTemplate;

    @Autowired
    private MessageDB messageDB;



    public void sendMessage(Message message,String routingKey,String exchangeName){

        //TODO 发送消息，消息入库 高并发场景下不适用
        //可用redis
        // 此处要设置消息唯一ID
        messageDB.insert(message);
        rabbitTemplate.send(message);

    }


    public void convertAndSend(){
        CorrelationData correlationData = new CorrelationData();

    }

    public void convertSendAndReceive(Message message){
        //只有确认消费者收到消息之后才会发送下一条消息
        rabbitTemplate.convertSendAndReceive(message);
    }

    public void receive(){

        rabbitTemplate.receive("queueName");
    }

    public void receiveAndConvert(){

        Object message = rabbitTemplate.receiveAndConvert("queueName");
    }

    public void convertAndSend(Message message){
        rabbitTemplate.convertAndSend(message,new MessagePostProcessorCustom());
    }


    public void send(String string){
        //同步方法，会有阻塞
        rabbitTemplate.convertSendAndReceive(string);
    }

    public void loopBack(){
        //TODO 轮询记录表，消息重试
        /**
         * 记录重试次数
         */
    }
}
