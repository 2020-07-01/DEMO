package com.cacheframework.manager.redis;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisClusterInfoCache;

/**
 * @ClassName : RetryAbleJedisCluster
 * @Author : yq
 * @Date: 2021-05-16
 * @Description :
 */
public abstract class RetryableJedisClusterPipeline {


    private final JedisClusterInfoCache clusterInfoCache;

    public RetryableJedisClusterPipeline(JedisCluster jedisCluster) {


        clusterInfoCache = getValue(connectionHandler, FIELD_CACHE);
    }

    public abstract void execute(JedisClusterPipeline jedisClusterPipeline);


    public void sync(){

        JedisClusterPipeline pipeline = new JedisClusterPipeline(clusterInfoCache);
        execute(pipeline);
        pipeline
    }


}
