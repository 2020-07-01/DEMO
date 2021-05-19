package com.cacheframework.manager.redis;

import com.cacheframework.common.MSetParam;
import com.cacheframework.core.CacheWrapper;
import com.cacheframework.serializer.ISerializer;
import com.cacheframework.core.CacheKeyTO;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName : JedisClusterCacheManager
 * @Author : yq
 * @Date: 2021-04-27
 * @Description : Redis 缓存管理
 */
public class JedisClusterCacheManager extends AbstractRedisCacheManager {

    private final JedisClusterClient redis;

    public JedisClusterCacheManager(JedisCluster jedisCluster, ISerializer<Object> serializer) {
        super(serializer);
        this.redis = new JedisClusterClient(jedisCluster, this);
    }

    @Override
    protected IRedis getRedis() {
        return redis;
    }

    public static class JedisClusterClient implements IRedis {

        private final JedisCluster jedisCluster;

        private final AbstractRedisCacheManager cacheManager;

        public JedisClusterClient(JedisCluster jedisCluster, AbstractRedisCacheManager cacheManager) {
            this.jedisCluster = jedisCluster;
            this.cacheManager = cacheManager;
        }

        @Override
        public void set(byte[] key, byte[] value) {
            jedisCluster.set(key, value);
        }

        /**
         * String 存储 过期时间
         *
         * @param key     缓存key
         * @param seconds 过期时间
         * @param value   缓存值
         */
        @Override
        public void setex(byte[] key, int seconds, byte[] value) {
            jedisCluster.setex(key, seconds, value);
        }

        /**
         * 批量删除缓存
         *
         * @param keys 缓存key
         */
        @Override
        public void delete(Set<CacheKeyTO> keys) {

            RetryableJedisClusterPipeline retryAbleJedisCluster = new RetryableJedisClusterPipeline(jedisCluster) {
                @Override
                public void execute(JedisClusterPipeline jedisClusterPipeline) {
                    JedisUtil.executeDelete(jedisClusterPipeline,keys);
                }
            };

            try {
retryAbleJedisCluster.sync();

            }catch (Exception e){

            }
        }

        @Override
        public byte[] get(byte[] key) {
            return jedisCluster.get(key);
        }

        @Override
        public byte[] hget(byte[] key, byte[] field) {
            return jedisCluster.hget(key, field);
        }

        @Override
        public void hset(byte[] key, byte[] field, byte[] value) {
            jedisCluster.hset(key, field, value);
        }

        @Override
        public void hset(byte[] key, byte[] field, byte[] value, int seconds) {

        }

        @Override
        public void mset(Collection<MSetParam> params) {

        }

        @Override
        public Map<CacheKeyTO, CacheWrapper> mget(Type returnType, Set<CacheKeyTO> keys) {
            return null;
        }


        @Override
        public void close() throws IOException {

        }
    }
}
