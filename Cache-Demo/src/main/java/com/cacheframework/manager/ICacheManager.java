package com.cacheframework.manager;

import com.cacheframework.core.CacheKeyTO;
import com.cacheframework.core.CacheWrapper;
import com.cacheframework.common.MSetParam;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName : ICacheManager
 * @Author : yq
 * @Date: 2021-04-27
 * @Description : 缓存管理
 */
public interface ICacheManager {


    /**
     * 当过期时间为0，说明永不过期
     */
    int NEVER_EXPIRE = 0;


    /**
     * 写数据
     *
     * @param cacheKey
     * @param result
     * @param method
     */
    void setCache(final CacheKeyTO cacheKey, final CacheWrapper<Object> result, final Method method);

    /**
     * 写数据
     *
     * @param method
     * @param params
     */
    void mset(final Method method, final Collection<MSetParam> params);

    /**
     * 根据缓存key获取缓存中的数据
     *
     * @param key
     * @param method
     * @return
     */
    CacheWrapper<Object> get(final CacheKeyTO key, final Method method) throws IOException;

    /**
     * @param method
     * @param returnType
     * @param keys
     * @return
     */
    Map<CacheKeyTO, CacheWrapper<Object>> mget(final Method method, final Type returnType, final Set<CacheKeyTO> keys);

    /**
     * @param keys
     */
    void delete(final Set<CacheKeyTO> keys);


}
