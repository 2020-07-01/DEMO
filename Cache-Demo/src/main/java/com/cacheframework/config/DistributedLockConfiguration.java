package com.cacheframework.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : DistributedLockConfiguration
 * @Author : yq
 * @Date: 2021-05-04
 * @Description : 分布式锁配置
 */
@Configuration
@AutoConfigureAfter({AutoloadCacheManagerConfiguration.class})
@Slf4j
public class DistributedLockConfiguration {

    /*@Bean
    @ConditionalOnMissingBean({ILock.class})
    @ConditionalOnClass(RedisConnectionFactory.class)
    @ConditionalOnBean(RedisConnectionFactory.class)
    public ILock autoLoadCacheDistributedLock(RedisConnectionFactory connectionFactory) {
        if (null == connectionFactory) {
            return null;
        }

        SpringRedisLock lock = new SpringRedisLock(connectionFactory);
        if (logger.isDebugEnabled()) {
            logger.debug("ILock with SpringJedisLock auto-configured");
        }
        return lock;
    }*/



}
