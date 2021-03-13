package com.rabbitmq.plan1;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * @ClassName : MessagePostProcessorAdapter
 * @Author : yq
 * @Date: 2021-03-13
 * @Description :
 */
public abstract class MessagePostProcessorAdapter implements MessagePostProcessor {

    /**
     * Change (or replace) the message.
     *
     * @param message the message.
     * @return the message.
     * @throws AmqpException an exception.
     */
    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        throw new UnsupportedOperationException();
    }
}
