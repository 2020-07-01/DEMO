package com.redisDemo;

import net.bytebuddy.asm.Advice;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName : RedisLockController
 * @Author : yq
 * @Date: 2021-01-24
 * @Description :
 */
@RequestMapping(value = "/deduct_stock")
public class RedisLockController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    Redisson redisson;

    /**
     * 分布式锁：
     * 在分布式环境下，秒杀或者抢购场景中
     * 多个线程竞争同一个资源
     * 抢票场景分析
     */

    @RequestMapping(value = "/test")
    public String dedcutStock() {

        String lockKey = "product";

        try {
            //加锁
            //设置过期时间10秒
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "lockEd", 10, TimeUnit.SECONDS);

            if (!result) {
                return "error";
            }

            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                stock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", stock + "");
                System.out.println("减票成功!");
            } else {
                System.out.println("减票失败,暂无余票!");
            }
        } finally {
            //解锁
            //释放锁
            stringRedisTemplate.delete(lockKey);
        }

        return "SUCCESS!";
    }


    @RequestMapping(value = "/test1")
    public String test1() {

        String lockKey = "product";
        //获取锁  多种api 会有阻塞情况
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            /*//加锁
            //设置过期时间10秒
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "lockEd", 10, TimeUnit.SECONDS);

            if (!result) {
                return "error";
            }*/


            //设置时间
            redissonLock.lock(30, TimeUnit.SECONDS);

            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                stock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", stock + "");
                System.out.println("减票成功!");
            } else {
                System.out.println("减票失败,暂无余票!");
            }
        } finally {
            //解锁
            //释放锁
            /*stringRedisTemplate.delete(lockKey);*/
            redissonLock.unlock();

        }

        return "SUCCESS!";
    }

}
