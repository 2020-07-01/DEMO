package com.redis;

import java.util.List;

/**
 * @ClassName : DataProducer
 * @Author : yq
 * @Date: 2021-05-25
 * @Description :
 */
public interface DataQueue {

    void push(String queueName, String message);

    List<String> pop(String queueName, int count);

    String pop(String queueName);
}






