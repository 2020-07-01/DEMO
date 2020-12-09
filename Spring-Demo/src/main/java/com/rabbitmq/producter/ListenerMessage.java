package com.rabbitmq.producter;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName : ListenerMessage
 * @Author : yq
 * @Date: 2020-12-06
 * @Description :
 */
@Component
public class ListenerMessage implements ChannelAwareMessageListener {


    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        System.out.println(new String(message.getBody()));

    }
}
