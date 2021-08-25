package com.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;
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
        log.info("biz msg:{}", string);
        if (string.endsWith("nack")) {
            //拒绝重新入队
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, false);
        } else {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
