package com.cacheframework.lock;

/**
 * @ClassName : AbstractRedisLock
 * @Author : yq
 * @Date: 2021-05-04
 * @Description : 基于redis 实现的分布式锁
 */
public abstract class AbstractRedisLock implements ILock {


    private static final String OK = "OK";

    public abstract boolean doTryLock(String key, String value, int expire);

    public abstract boolean doReleaseLock(String key, String value);

    /**
     * 获取分布式锁
     *
     * @param key        锁key
     * @param lockExpire 锁的缓存时间(单位：秒)
     * @return boolean
     */
    @Override
    public boolean tryLock(String key, int lockExpire) {
        boolean locked = doTryLock(key, OK, lockExpire);
        //TODO 任务阻塞锁自动释放 或者 任务失败 锁无法释放
        return locked;
    }

    /**
     * 释放锁
     *
     * @param key 锁key
     */
    @Override
    public void releaseLock(String key, String value) {
        doReleaseLock(key,value);
    }
}
