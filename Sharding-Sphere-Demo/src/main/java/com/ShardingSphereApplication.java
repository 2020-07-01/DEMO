package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName : ShardingSphereApplication
 * @Author : yq
 * @Date: 2021-02-21
 * @Description :
 */
@MapperScan("com.sharding.demo.mapper")
@SpringBootApplication
public class ShardingSphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereApplication.class, args);
    }
}
