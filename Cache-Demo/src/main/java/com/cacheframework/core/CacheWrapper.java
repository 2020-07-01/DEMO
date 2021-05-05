package com.cacheframework.core;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName : CacheWrapper
 * @Author : yq
 * @Date: 2021-04-27
 * @Description : 缓存数据包装类
 */
@Data
public class CacheWrapper<T> implements Serializable, Cloneable {

    /**
     * 缓存数据
     */
    private T cacheObject;

    /**
     * 最后从DAO中加载时间
     */
    private long lastLoadTime;

    /**
     * 缓存时长
     */
    private int expire;

    public CacheWrapper() {
    }

    public CacheWrapper(T cacheObject, int expire) {
        this.cacheObject = cacheObject;
        this.lastLoadTime = System.currentTimeMillis();
        this.expire = expire;
    }


    /**
     * 判断缓存是否过期
     *
     * @return
     */
    public boolean isExpired() {
        if (expire > 0) {
            return (System.currentTimeMillis() - lastLoadTime) > expire * 1000;
        }
        return false;
    }

}
