package com.cacheframework.config;

import com.cacheframework.manager.ICacheManager;
import com.cacheframework.manager.map.MapCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : AutoloadCacheManagerConfiguration
 * @Author : yq
 * @Date: 2021-04-28
 * @Description :
 */
@Slf4j
@Configuration
public class AutoLoadCacheConfiguration {

    @Bean
    public ICacheManager mapCacheManager(AutoloadCacheProperties cacheProperties) {
        return new MapCacheManager(cacheProperties.getConfig());
    }

}
