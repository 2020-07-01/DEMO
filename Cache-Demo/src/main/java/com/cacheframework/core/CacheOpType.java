package com.cacheframework.core;

/**
 * 缓存操作类型
 */
public enum CacheOpType {

    /**
     * 如果缓存中有数据则读取缓存中的数据，如果没有数据则读取数据源中的数据，并写入缓存
     */
    READ_WRITE,
    /**
     * 从数据源中加载最新的数据，并写入缓存
     */
    WRITE,
    /**
     * 只从缓存中读取数据
     */
    READ_ONLY,
    /**
     * 直接从数据源中获取数据，不读取缓存也不写入缓存
     * 适用于登录等
     * 或者操作不频繁的场景
     */
    LOAD
}