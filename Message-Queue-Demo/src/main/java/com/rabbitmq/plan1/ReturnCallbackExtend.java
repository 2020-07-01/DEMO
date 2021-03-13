package com.rabbitmq.plan1;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


/**
 * @ClassName : ReturnCallbackExtend
 * @Author : yq
 * @Date: 2021-03-08
 * @Description : 保证消息投递到queue
 */
@Component
public class ReturnCallbackExtend implements RabbitTemplate.ReturnCallback {

    /**
     * template.setMandatory(true);
     * 当交换机通过路由key找不到队列时
     * 消息返回给生产者
     * 如果不开启，则消息将会被丢弃
     *
     * 也可设置备份交换机存储
     */

    /**
     * Returned message callback.
     *
     * @param message    the returned message.
     * @param replyCode  the reply code.
     * @param replyText  the reply text.
     * @param exchange   the exchange.
     * @param routingKey the routing key.
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

        //TODO
        String messageId = message.getMessageProperties().getMessageId();

    }
}
