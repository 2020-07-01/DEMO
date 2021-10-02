package com.xxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName : ConsumerApplivation
 * @Author : yq
 * @Date: 2021-10-02
 * @Description :
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConsumerApplication.class);
    }
}
