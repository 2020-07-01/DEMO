package com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.NumberUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName : RedisClusterConfig
 * @Author : yq
 * @Date: 2021-04-13
 * @Description :
 */
@Configuration
@ConditionalOnClass({JedisCluster.class})
public class RedisClusterConfig{

    @Value("${spring.redis.clusterNodes}")
    private String clusterNodes;
    @Value("${spring.redis.expireSeconds:120L}")
    private long expireSeconds;
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;
    @Value("${spring.redis.commandTimeout}")
    private int commandTimeout;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.max-redirects}")
    private int maxAttempts;

    @Bean
    public JedisCluster jedisCluster() {
        //多节点
        String[] nodeArray = clusterNodes.split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        for (String node : nodeArray) {
            String[] hp = node.split(":");
            nodes.add(new HostAndPort(hp[0], Integer.valueOf(hp[1])));
        }

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);

        return new JedisCluster(nodes, commandTimeout, commandTimeout, maxAttempts, password, jedisPoolConfig);
    }

}
