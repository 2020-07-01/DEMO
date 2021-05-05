package com.cacheframework.manager.redis;

import com.cacheframework.common.MSetParam;
import com.cacheframework.core.CacheKeyTO;
import com.cacheframework.core.CacheWrapper;
import com.cacheframework.manager.ICacheManager;
import com.cacheframework.serializer.ISerializer;
import com.cacheframework.serializer.StringSerializer;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName : AbstractRedisCacheManager
 * @Author : yq
 * @Date: 2021-04-27
 * @Description :
 */
public abstract class AbstractRedisCacheManager implements ICacheManager {

    public static final StringSerializer KEY_SERIALIZER = new StringSerializer();

    protected final ISerializer<Object> serializer;

    public AbstractRedisCacheManager(ISerializer<Object> serializer) {
        this.serializer = serializer;
    }

    protected abstract IRedis getRedis();

    /**
     * 写数据
     *
     * @param cacheKey
     * @param result
     * @param method
     */
    @Override
    public void setCache(CacheKeyTO cacheKey, CacheWrapper<Object> result, Method method) {

    }

    /**
     * 写数据
     *
     * @param method
     * @param params
     */
    @Override
    public void mset(Method method, Collection<MSetParam> params) {

    }

    /**
     * 根据缓存key获取缓存中的数据
     *
     * @param cacheKeyTo
     * @param method
     * @return
     */
    @Override
    public CacheWrapper<Object> get(CacheKeyTO cacheKeyTo, Method method) throws IOException {
        if (null == cacheKeyTo) {
            return null;
        }
        String cacheKey = cacheKeyTo.getKey();
        if (null == cacheKey || cacheKey.isEmpty()) {
            return null;
        }
        CacheWrapper<Object> res = null;
        String hfield;
        Type returnType = null;
        try (IRedis redis = getRedis()) {
            byte[] val;

            hfield = cacheKeyTo.getHfield();
            if (null == hfield || hfield.isEmpty()) {
                val = redis.get(KEY_SERIALIZER.serialize(cacheKey));
            } else {
                val = redis.hget(KEY_SERIALIZER.serialize(cacheKey), KEY_SERIALIZER.serialize(hfield));
            }
            if (null != method) {
                returnType = method.getGenericReturnType();
            }
            res = (CacheWrapper<Object>) serializer.deserialize(val, returnType);
        } catch (Exception e) {

        }
        return res;
    }

    /**
     * @param method
     * @param returnType
     * @param keys
     * @return
     */
    @Override
    public Map<CacheKeyTO, CacheWrapper<Object>> mget(Method method, Type returnType, Set<CacheKeyTO> keys) {
        return null;
    }

    /**
     * @param keys
     */
    @Override
    public void delete(Set<CacheKeyTO> keys) {

    }
}


