package com.cacheframework.lock;

/**
 * @ClassName : Lock
 * @Author : yq
 * @Date: 2021-05-04
 * @Description : 分布式锁接口
 */
public interface ILock {

    /**
     * 获取分布式锁
     *
     * @param key        锁key
     * @param lockExpire 锁的缓存时间(单位：秒)
     * @return boolean
     */
    boolean tryLock(String key, int lockExpire);

    /**
     * 释放锁
     *
     * @param key   锁key
     * @param value 锁value
     */
    void releaseLock(String key, String value);
}


