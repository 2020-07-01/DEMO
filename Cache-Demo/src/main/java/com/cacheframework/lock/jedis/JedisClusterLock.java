package com.cacheframework.lock.jedis;

import com.cacheframework.lock.AbstractRedisLock;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

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
     * 获取锁
     *
     * @param key    key
     * @param value  value
     * @param expire expire
     * @return boolean
     */
    @Override
    public boolean doTryLock(String key, String value, int expire) {
        //todo redis 集群中会出问题
        //res = "OK".equals(jedisCluster.set(key, value, SetParams.setParams().nx().ex(expire)));
        return "OK".equals(jedisCluster.setex(key, expire, value));
    }

    /**
     * 释放锁
     *
     * @param key   key
     * @param value value
     * @return boolean
     */
    @Override
    public boolean doReleaseLock(String key, String value) {
        String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                "return redis.call('del',KEYS[1]) else return 0 end";
        return jedisCluster.eval(luaScript, Collections.singletonList(key), Collections.singletonList(value)).equals(1L);
    }
}
