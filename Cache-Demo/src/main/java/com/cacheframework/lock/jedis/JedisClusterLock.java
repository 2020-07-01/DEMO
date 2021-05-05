package com.cacheframework.lock.jedis;

import com.cacheframework.lock.AbstractRedisLock;
import redis.clients.jedis.JedisCluster;

/**
 * @ClassName : JedisClusterLock
 * @Author : yq
 * @Date: 2021-05-04
 * @Description :
 */
public class JedisClusterLock extends AbstractRedisLock {

    private JedisCluster jedisCluster;

    public JedisClusterLock(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    /**
     * 获取分布式锁
     *
     * @param key        锁key
     * @param lockExpire 锁的缓存时间(单位：秒)
     * @return boolean
     */
    @Override
    public boolean tryLock(String key, int lockExpire) {
        return false;
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
