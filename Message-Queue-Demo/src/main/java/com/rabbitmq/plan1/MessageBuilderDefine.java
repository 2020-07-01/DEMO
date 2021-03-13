package com.rabbitmq.plan1;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;

import java.io.Serializable;
import java.util.Date;


/**
 * @ClassName : ContentMessageBuilder
 * @Author : yq
 * @Date: 2021-03-13
 * @Description : 消息构造器 只否则消息构造
 */
public class MessageBuilderDefine {

    private static MessageConverter messageConverter = new SimpleMessageConverter();

    /**
     * @param content
     * @param messageId
     * @param <T>
     * @return
     */
    public static <T> Message buildMessage(T content, String messageId) {
        /**
         * 设置消息属性，包括队列，交换机，消息id等
         */
        MessageProperties messageProperties = MessagePropertiesBuilder.newInstance().setTimestamp(new Date()).setMessageId(messageId).build();
        //非序列化非String非字节数组
        if (!(content instanceof Serializable) && (content instanceof String) && !(content instanceof Byte)) {
            Gson gson = new Gson();
            String stringMessage = gson.toJson(content);
            return messageConverter.toMessage(stringMessage, messageProperties);
        } else {
            return messageConverter.toMessage(content, messageProperties);
        }
    }


}
