package com.cacheframework.autoload;

import com.cacheframework.core.CacheKeyTO;
import com.cacheframework.annotation.Cache;
import com.cacheframework.common.CacheAopProxyChain;
import com.cacheframework.core.CacheWrapper;
import lombok.Data;

/**
 * @ClassName : AutoLoadTO
 * @Author : yq
 * @Date: 2021-05-04
 * @Description : 处理自动加载数据类
 */
@Data
public class AutoLoadTO {

    private final Object[] args;

    /**
     * 缓存Key
     */
    private final CacheKeyTO cacheKey;

    private final CacheAopProxyChain joinPoint;

    /**
     * 缓存注解
     */
    private final Cache cache;

    /**
     * 请求数据次数
     */
    private long requestTimes = 0L;

    /**
     * 缓存时长
     */
    private int expire;

    /**
     * 上次请求数据的时间
     */
    private long lastRequestTime = 0L;

    /**
     * 第一次请求数据时间
     */
    private long firstRequestTime = 0L;

    /**
     * 上次从DAO加载数据时间
     */
    private long lastLoadTime = 0L;

    /**
     * 从DAO中加载数据，使用时间的总和
     */
    private long useTotalTime = 0L;

    private volatile boolean loading = false;

    /**
     * 加载次数
     */
    private long loadCount = 0L;

    public AutoLoadTO(CacheKeyTO cacheKey, CacheAopProxyChain joinPoint, Object args[], Cache cache, int expire) {
        this.cacheKey = cacheKey;
        this.joinPoint = joinPoint;
        this.args = args;
        this.cache = cache;
        this.expire = expire;
    }

    public Object[] getArgs() {
        return args;
    }

    /**
     * 最后请求时间
     *
     * @param lastRequestTime lastRequestTime
     * @return AutoLoadTO
     */
    public AutoLoadTO setLastRequestTime(long lastRequestTime) {
        synchronized (this) {
            this.lastRequestTime = lastRequestTime;
            if (firstRequestTime == 0) {
                firstRequestTime = lastRequestTime;
            }
            requestTimes++;
        }
        return this;
    }

    /**
     * @param loading 是否正在加载
     * @return this
     */
    public AutoLoadTO setLoading(boolean loading) {
        this.loading = loading;
        return this;
    }


    /**
     * 刷新请求次数
     *
     * @param cacheWrapper
     */
    public void flushRequestTime(CacheWrapper<Object> cacheWrapper) {
        if (cacheWrapper == null) {
            return;
        }
        //同步最后请求时间
        this.setLastRequestTime(System.currentTimeMillis())
                //同步从DAO加载时间
                .setLastLoadTime(cacheWrapper.getLastLoadTime())
                //同步过期时间
                .setExpire(cacheWrapper.getExpire());
    }

    /**
     * @param expire expire
     * @return this
     */
    public AutoLoadTO setExpire(int expire) {
        this.expire = expire;
        return this;
    }


    /**
     * @param lastLoadTime last load time
     * @return this
     */
    public AutoLoadTO setLastLoadTime(long lastLoadTime) {
        this.lastLoadTime = lastLoadTime;
        return this;
    }


    /**
     * 记录用时
     *
     * @param useTime
     */
    public void addUseTotalTime(long useTime) {

    }

    /**
     * 平均耗时
     *
     * @return long 平均耗时
     */
    public long getAverageUseTime() {
        if (loadCount == 0) {
            return 0;
        }
        return this.useTotalTime / loadCount;
    }

}
