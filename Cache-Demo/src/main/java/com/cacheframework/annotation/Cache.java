package com.cacheframework.annotation;

import com.cacheframework.core.CacheOpType;

public @interface Cache {

    /**
     * 自定义缓存key
     *
     * @return String
     */
    String key();

    /**
     * 设置哈希表中的字段，如果设置此项，则用哈希表进行存储，支持表达式
     *
     * @return String
     */
    String hfield() default "";

    /**
     * 缓存操作类型：默认是READ_WRITE 先从缓存中去数据，如果没有取到则从DAO中取数据，并写入缓存
     *
     * @return
     */
    CacheOpType opType() default CacheOpType.READ_WRITE;

    /**
     * 缓存的过期时间，单位：秒，如果为0则表示永久缓存
     *
     * @return 时间
     */
    int expire();

    /**
     * 开启自动加载时，如果缓存数据在requestTimeout时间内没有使用，则不进行自动加载
     * 如果requestTimeout为0时，会一直自动加载
     *
     * @return
     */
    long requestTimeout() default 36000L;

    /**
     * 是否总是缓存 常驻内存
     * 如果开启则缓存的有效期为永久
     *
     * @return
     */
    boolean alwaysCache() default false;

    /**
     * 预警自动刷新时间(单位：秒)，必须满足 0 &lt; alarmTime &lt; expire才有效 当缓存在alarmTime
     * 时间内即将过期的话，会自动刷新缓存内容；
     *
     * @return 时间
     */
    int alarmTime() default 0;

    /**
     * 分布式锁的缓存时间(单位：秒)
     * 在设置分布式锁的前提下，如果设置此项大于0，则会使用分布式锁
     * 如果设置此项为0，则不使用分布式锁
     *
     * @return 分布式锁的缓存时间
     */
    int lockExpire() default 10;

    /**
     * 是否开启锁降级
     * 默认不开启;
     * 如果开启，当分布式锁抛异常时不使用分布式锁
     */
    boolean openLockDown() default false;

    /**
     * 并发等待时间(毫秒)
     * 等待正在从DAO中加载数据的线程返回的等待时间
     *
     * @return 时间
     */
    int waitTimeOut() default 500;

}
