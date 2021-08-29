package com.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : RabbitMqConfig
 * @Author : yq
 * @Date: 2021-08-24
 * @Description :
 */
@Slf4j
@EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
@EnableConfigurationProperties({RabbitMqProperties.class})
@Configuration
public class RabbitMqConfig {

    @Configuration
    @EnableConfigurationProperties({RabbitMqProperties.class})
    public static class Biz {

        private final RabbitMqProperties rabbitMqProperties;

        public Biz(RabbitMqProperties rabbitMqProperties) {
            this.rabbitMqProperties = rabbitMqProperties;
        }

        @Bean(value = "bizConnectionFactory")
        public ConnectionFactory connectionFactory() {

            CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(rabbitMqProperties.getHost(), rabbitMqProperties.getPort());
            cachingConnectionFactory.setUsername(rabbitMqProperties.getUsername());
            cachingConnectionFactory.setPassword(rabbitMqProperties.getPassword());
            cachingConnectionFactory.setVirtualHost(rabbitMqProperties.getVirtualHost());
            return cachingConnectionFactory;
        }

        @Bean(value = "rabbitTemplate")
        public RabbitTemplate rabbitTemplate(@Qualifier("bizConnectionFactory") ConnectionFactory connectionFactory) {
            RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
            rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
                if (ack) {
                    log.info("消息发送成功!");
                } else {
                    log.info("消息发送失败!");
                }
            });
            //监听消息回调
            rabbitTemplate.setMandatory(true);
            rabbitTemplate.containerAckMode(AcknowledgeMode.MANUAL);
            rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}",
                    exchange, routingKey, replyCode, replyText, message));
            return rabbitTemplate;
        }

        @Bean("bizRabbitAdmin")
        public RabbitAdmin rabbitAdmin(@Qualifier("bizConnectionFactory") ConnectionFactory connectionFactory) {
            return new RabbitAdmin(connectionFactory);
        }

        @Bean
        public RabbitMqDeclare deadQueueRabbitMqDeclare(@Qualifier("bizRabbitAdmin") RabbitAdmin rabbitAdmin) {
            return new RabbitMqDeclare(rabbitAdmin);
        }

        @Bean("bizContainer")
        public SimpleMessageListenerContainer simpleMessageListenerContainer(@Qualifier("bizConnectionFactory") ConnectionFactory connectionFactory,
                                                                             @Qualifier("bizMessageListener") BizMessageListener messageListener) {
            SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
            //设置监听的队列
            simpleMessageListenerContainer.setQueueNames("bizQueue");
            simpleMessageListenerContainer.setQueues(new Queue("bizQueue"));
            //待研究
            simpleMessageListenerContainer.setReceiveTimeout(1000);
            //设置消息后置处理器
            simpleMessageListenerContainer.setAfterReceivePostProcessors(new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().getHeaders().put("queue","biz");
                    return message;
                }
            });
            //设置监听者
            simpleMessageListenerContainer.setMessageListener(messageListener);
            //设置消息确认模式
            simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
            //设置自动创建
            simpleMessageListenerContainer.setAutoDeclare(true);
            //设置当前消费者的数量
            simpleMessageListenerContainer.setConcurrentConsumers(5);
            //设置最大消费者的数量
            simpleMessageListenerContainer.setMaxConcurrentConsumers(10);
            //设置批量处理消息数量
            simpleMessageListenerContainer.setBatchSize(3);
            //设置已消费但未ack的消息的数量，即Unacked
            simpleMessageListenerContainer.setPrefetchCount(1);

            return simpleMessageListenerContainer;
        }
    }

    @Configuration
    @EnableConfigurationProperties({RabbitMqProperties.class})
    public static class Dead {

        private final RabbitMqProperties rabbitMqProperties;

        public Dead(RabbitMqProperties rabbitMqProperties) {
            this.rabbitMqProperties = rabbitMqProperties;
        }

        @Bean(value = "deadConnectionFactory")
        public ConnectionFactory connectionFactory() {

            CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(rabbitMqProperties.getHost(), rabbitMqProperties.getPort());
            cachingConnectionFactory.setUsername(rabbitMqProperties.getUsername());
            cachingConnectionFactory.setPassword(rabbitMqProperties.getPassword());
            cachingConnectionFactory.setVirtualHost(rabbitMqProperties.getVirtualHost());
            return cachingConnectionFactory;
        }

        @Bean(value = "deadRabbitTemplate")
        public RabbitTemplate rabbitTemplate(@Qualifier("deadConnectionFactory") ConnectionFactory connectionFactory) {
            RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
            rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
                if (ack) {
                    log.info("消息发送成功!");
                } else {
                    log.info("消息发送成功!");
                }
            });
            /**
             * mandatory:true 当消息找不到队列时会返还给生产者
             */
            rabbitTemplate.setMandatory(true);
            //监听消息回调
            rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
                log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}",
                        exchange, routingKey, replyCode, replyText, message);
            });
            return new RabbitTemplate(connectionFactory);
        }

        @Bean("deadRabbitAdmin")
        public RabbitAdmin rabbitAdmin(@Qualifier("deadConnectionFactory") ConnectionFactory connectionFactory) {
            return new RabbitAdmin(connectionFactory);
        }

        @Bean("deadContainer")
        public SimpleMessageListenerContainer simpleMessageListenerContainer(@Qualifier("deadConnectionFactory") ConnectionFactory connectionFactory,
                                                                             @Qualifier("deadMessageListener") DeadMessageListener deadMessageListener) {
            SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
            simpleMessageListenerContainer.addQueueNames("deadQueue");
            simpleMessageListenerContainer.setMessageListener(deadMessageListener);
            simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
            return simpleMessageListenerContainer;
        }
    }

}
