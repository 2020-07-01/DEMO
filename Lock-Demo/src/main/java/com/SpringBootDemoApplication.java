package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @ClassName : SpringBootDemoApplication
 * @Author : yq
 * @Date: 2021-08-24
 * @Description :
 */
@EnableConfigurationProperties
@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class);
    }
}
