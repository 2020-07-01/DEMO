package com.cacheframework.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName : RedissonLock
 * @Author : yq
 * @Date: 2021-05-04
 * @Description :
 */
public class RedissonLock extends AbstractRedisLock {

    @Autowired
    Redisson redisson;

    /**
     * 获取分布式锁
     *
     * @param key        锁key
     * @param lockExpire 锁的缓存时间(单位：秒)
     * @return boolean
     */
    @Override
    public boolean tryLock(String key, int lockExpire) {
        RLock redissonLock = redisson.getLock(key);
        //设置时间
        redissonLock.lock(30, TimeUnit.SECONDS);
        return true;
    }

    /**
     * 释放锁
     *
     * @param key 锁key
     */
    @Override
    public void unLock(String key) {

    }
}
