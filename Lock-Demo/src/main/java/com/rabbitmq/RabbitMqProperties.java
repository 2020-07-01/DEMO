package com.rabbitmq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName : RabbitMqProperties
 * @Author : yq
 * @Date: 2021-08-24
 * @Description :
 */
@Data
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMqProperties {

    private String host;

    private int port;

    private String username;

    private String password;

    private String virtualHost;

    private Integer maxChannel = 64;

    private Integer channelCacheSize = 256;


}
