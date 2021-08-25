package com.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : DeadQueueRabbitMqDeclare
 * @Author : yq
 * @Date: 2021-08-24
 * @Description :
 */
public class RabbitMqDeclare implements SmartLifecycle {

    private RabbitAdmin rabbitAdmin;

    public RabbitMqDeclare(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    public void declare() {

        //声明业务交换机
        Exchange bizExchange = ExchangeBuilder.fanoutExchange("bizExchange").build();

        //死信交换机
        Exchange deadExchange = ExchangeBuilder.directExchange("deadExchange").durable(true).build();

        //声明业务队列A
        Map<String, Object> args = new HashMap<>(16);
        //声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", deadExchange.getName());
        //声明当前队列绑定的死信路由key 死信交换机路由到指定死信队列
        args.put("x-dead-letter-routing-key", "dead-routing-key");
        Queue bizQueue = QueueBuilder.durable("bizQueue").withArguments(args).build();

        //业务交换机绑定业务队列
        rabbitAdmin.declareQueue(bizQueue);
        rabbitAdmin.declareExchange(bizExchange);
        rabbitAdmin.declareBinding(new Binding(bizQueue.getName(), Binding.DestinationType.QUEUE, bizExchange.getName(), "biz-routing-key", Collections.emptyMap()));

        //声明死信队列A
        Queue deadQueue = QueueBuilder.durable("deadQueue").build();
        //死信交换机绑定死信队列
        rabbitAdmin.declareQueue(deadQueue);
        rabbitAdmin.declareExchange(deadExchange);
        rabbitAdmin.declareBinding(new Binding(deadQueue.getName(), Binding.DestinationType.QUEUE, deadExchange.getName(), "dead-routing-key", Collections.emptyMap()));
    }


    @Override
    public void start() {
        declare();
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
