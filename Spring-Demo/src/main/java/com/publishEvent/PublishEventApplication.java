package com.publishEvent;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

import java.util.concurrent.*;

/**
 * @ClassName : PublishEventApplication
 * @Author : yq
 * @Date: 2020-12-06
 * @Description :
 */
@SpringBootApplication
public class PublishEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublishEventApplication.class, args);
    }

   /* *//**
     * 定义线程池
     *
     * @return
     *//*
    @Bean
    public SimpleApplicationEventMulticaster simpleApplicationEventMulticaster() {
        BlockingQueue queue = new DelayQueue();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Executor executor = new ThreadPoolExecutor(10, 20, 5000, TimeUnit.SECONDS, queue, threadFactory);

        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster();
        simpleApplicationEventMulticaster.setTaskExecutor(executor);
        return simpleApplicationEventMulticaster;
    }*/
}

