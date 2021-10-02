package com.xxx.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName : CustomGateway
 * @Author : yq
 * @Date: 2021-10-02
 * @Description : 自定义网关过滤器
 */
/*@Slf4j
public class CustomGateway implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("自定义网关路由器");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}*/
