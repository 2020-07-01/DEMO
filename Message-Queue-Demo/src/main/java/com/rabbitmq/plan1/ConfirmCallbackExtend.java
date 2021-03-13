package com.rabbitmq.plan1;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName : ConfirmCallbackExtend
 * @Author : yq
 * @Date: 2021-03-08
 * @Description : 保证消息投递到Exchange
 */
@Component
public class ConfirmCallbackExtend implements RabbitTemplate.ConfirmCallback {



    /**
     * 开启confirm确认机制之后，所有在该信道上发送的消息都会指定唯一的id
     */

    /**
     * Confirmation callback.
     *
     * @param correlationData correlation data for the callback. 包含Message
     * @param ack             true for ack, false for nack
     * @param cause           An optional cause, for nack, when available, otherwise null.
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        String messageId = correlationData.getReturnedMessage().getMessageProperties().getMessageId();
        //如果投递成功
        if (ack) {
            //TODO 更新记录表中消息的状态


        } else {
            //投递失败
            //TODO 更新记录表中消息的状态

            //根据唯一ID 更新状态

        }
    }
}
