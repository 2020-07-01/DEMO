package com.cacheframework.manager.redis;

import com.cacheframework.serializer.ISerializer;
import com.cacheframework.core.CacheKeyTO;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
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

        }

        /**
         * 批量删除缓存
         *
         * @param keys
         */
        @Override
        public void delete(Set<CacheKeyTO> keys) {

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
        public void close() throws IOException {

        }
    }
}
