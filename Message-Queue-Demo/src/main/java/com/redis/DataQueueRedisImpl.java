package com.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @ClassName : DataQueueRedisImpl
 * @Author : yq
 * @Date: 2021-05-25
 * @Description :
 */
public abstract class DataQueueRedisImpl extends AbstractDataQueue {

    Jedis jedis;


    @Override
    public void push(String queueName, String message) {
        jedis.rpush(queueName, message);
    }

    @Override
    public List<String> pop(String queueName, int count) {

        for (int i = 0; i < count; i++) {

        }
        return null;
    }

    @Override
    public String pop(String queueName) {
        return jedis.lpop(queueName);
    }
}
