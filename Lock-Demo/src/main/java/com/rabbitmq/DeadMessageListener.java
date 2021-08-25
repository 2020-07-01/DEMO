package com.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName : DeadMessageListener
 * @Author : yq
 * @Date: 2021-08-24
 * @Description :
 */
@Slf4j
@Component
public class DeadMessageListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("dead message:{}", msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}
