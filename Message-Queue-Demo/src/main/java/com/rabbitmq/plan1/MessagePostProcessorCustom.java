package com.rabbitmq.plan1;

import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.core.Message;

/**
 * @ClassName : MessagePostProcessorCustom
 * @Author : yq
 * @Date: 2021-03-13
 * @Description : 在消息转换完成之后对消息进行一些处理
 */
public class MessagePostProcessorCustom extends MessagePostProcessorAdapter {


    /**
     * Change (or replace) the message and/or change its correlation data.
     *
     * @param message     the message.
     * @param correlation the correlation data.
     * @return the message.
     * @since 1.6.7
     */
    @Override
    public Message postProcessMessage(Message message, Correlation correlation) {

        return null;
    }
}
