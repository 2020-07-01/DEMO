package com.cacheframework.manager.redis;

import com.cacheframework.common.MSetParam;
import com.cacheframework.core.CacheKeyTO;
import com.cacheframework.core.CacheWrapper;

import java.io.Closeable;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName : IRedis
 * @Author : yq
 * @Date: 2021-04-27
 * @Description : reids 缓存操作
 */
public interface IRedis extends Closeable {

    /**
     * 批量删除缓存
     *
     * @param keys
     */
    void delete(Set<CacheKeyTO> keys);

    /**
     * String 存储
     *
     * @param key
     * @param value
     */
    void set(final byte[] key, final byte[] value);

    /**
     * String 存储 过期时间
     *
     * @param key
     * @param seconds
     * @param value
     */
    void setex(final byte[] key, int seconds, final byte[] value);

    /**
     * String 获取数据
     *
     * @param key
     * @return
     */
    byte[] get(byte[] key);

    byte[] hget(final byte[] key, final byte[] field);

    void hset(byte[] key, byte[] field, byte[] value);

    void hset(byte[] key, byte[] field, byte[] value, int seconds);

    void mset(final Collection<MSetParam> params);

    Map<CacheKeyTO, CacheWrapper> mget(final Type returnType, final Set<CacheKeyTO> keys);
}
