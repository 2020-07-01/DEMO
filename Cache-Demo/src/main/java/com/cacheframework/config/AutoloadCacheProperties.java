package com.cacheframework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName : AutoloadCacheProperties
 * @Author : yq
 * @Date: 2021-04-28
 * @Description :
 */
@Data
@ConfigurationProperties(prefix = "AutoloadCacheProperties.")
public class AutoloadCacheProperties {

    public static final String PREFIX = "autoload.cache";

    private AutoLoadConfig config = new AutoLoadConfig();

    private boolean enable = true;

    /**
     * 对JedisClusterCacheManager 进行配置
     */
    @Data
    static class JedisCacheManagerConfig {
        /**
         * Hash的缓存时长，等于0时永久缓存
         * 大于0时，防止一些不经常使用的缓存占用内存
         * 小于0时，则使用@Cache 中设置的expire值
         */
        private int hashExpire = -1;
    }
}
