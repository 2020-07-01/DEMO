package com.cacheframework.manager.map;

import com.cacheframework.config.AutoLoadConfig;
import com.cacheframework.core.CacheKeyTO;
import com.cacheframework.core.CacheWrapper;
import com.cacheframework.common.MSetParam;
import com.cacheframework.manager.ICacheManager;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName : MapCacheManager
 * @Author : yq
 * @Date: 2021-04-28
 * @Description : MapCacheManager 作为缓存
 */
@Slf4j
public class MapCacheManager implements ICacheManager {

    private final ConcurrentHashMap<String, Object> cache;

    private final CacheChangeListener cacheChangeListener;

    private final AutoLoadConfig config;

    private CacheTask cacheTask;

    private Thread thread = null;

    /**
     * 往缓存中写数据，是否把克隆后的值放入缓存
     * true:拷贝缓存值，避免外界修改缓存值
     * false:不拷贝缓存值，缓存值可能被外界修改，效率高
     */
    private boolean copyValueOnSet = false;

    public MapCacheManager(AutoLoadConfig config, int initSize) {
        this.cache = new ConcurrentHashMap<>(initSize);
        this.config = config;
        cacheTask = new CacheTask(this);
        this.cacheChangeListener = cacheTask;
    }

    public MapCacheManager(AutoLoadConfig config) {
        this(config, 1024);
    }


    /**
     * 写数据
     *
     * @param cacheKeyTO 缓存key
     * @param result     缓存数据包装类
     * @param method     方法
     */
    @Override
    public void setCache(final CacheKeyTO cacheKeyTO, final CacheWrapper<Object> result, final Method method) {
        if (null == cacheKeyTO) {
            return;
        }
        if (result.getExpire() < 0) {
            return;
        }

        String cacheKey = cacheKeyTO.getKey();
        if (cacheKey == null || cacheKey.isEmpty()) {
            return;
        }
        CacheWrapper<Object> value = null;
        //TODO

        SoftReference<CacheWrapper<Object>> reference = new SoftReference<>(value);
        String hfield = cacheKeyTO.getHfield();
        if (null == hfield || hfield.isEmpty()) {
            cache.put(cacheKey, reference);
        }


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
     * @param key
     * @param method
     * @return
     */
    @Override
    public CacheWrapper<Object> get(CacheKeyTO key, Method method) {
        return null;
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
