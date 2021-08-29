package com.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

/**
 * @ClassName : RabbitMqListener
 * @Author : yq
 * @Date: 2021-08-24
 * @Description :
 */
@Slf4j
@Service
public class BizMessageListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String string = new String(message.getBody());
        log.info("header:{},biz msg:{}", string,message.getMessageProperties().getHeaders(),string);
        if (string.endsWith("1")) {
            /**
             * 拒绝本条消息之前的所有未确认的消息，不再重新入队
             * multiple:是否拒绝本条消息之前所有未确认的消息
             * requeue:消息是否重新入队
             */
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, false);
        } else if (string.endsWith("2")) {
            //拒绝本条消息，不再重新入队
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        } else if (string.endsWith("3")) {
            //拒绝消息，重新放回队列
            channel.basicRecover(true);
        } else if (string.endsWith("4")) {
            //消息重新，重新投递给当前消费者
            channel.basicRecover(false);
        } else if (string.endsWith("5")) {
            //拒绝当前消息，重新入队
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        } else if (string.endsWith("6")) {
            //拒绝当前消息，不重新入队
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        } else if (string.endsWith("7")) {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } else if (string.endsWith("8")) {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
