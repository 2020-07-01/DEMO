package com.cacheframework.manager.redis;

import com.cacheframework.core.CacheKeyTO;

import java.io.Closeable;
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

    void set(final byte[] key, final byte[] value);

    byte[] get(byte[] key);

    byte[] hget(final byte[] key, final byte[] field);

    void hset(byte[] key, byte[] field, byte[] value);


}
