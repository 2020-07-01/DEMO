package com.rabbitmq.plan1;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

/**
 * @ClassName : MessageListener
 * @Author : yq
 * @Date: 2021-03-09
 * @Description : 消息监听
 */
public abstract class MessageListener implements ChannelAwareMessageListener {


    /**
     * 如何接受消息
     */


    /**
     * Callback for processing a received Rabbit message.
     * <p>Implementors are supposed to process the given Message,
     * typically sending reply messages through the given Session.
     *
     * @param message the received AMQP message (never <code>null</code>)
     * @param channel the underlying Rabbit Channel (never <code>null</code>)
     * @throws Exception Any.
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {


        //幂等操作
        MessageProperties messageProperties = message.getMessageProperties();


        //如果忘记消息ack,则rabbtitmq会一直往队列发送消息，OOM
        channel.basicAck(messageProperties.getDeliveryTag(), false);
    }
}
