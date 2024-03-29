package com.xxx.filter;

import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : GatewayRoutesConfiguration
 * @Author : yq
 * @Date: 2021-10-02
 * @Description : 网关路由配置类
 */
@Configuration
public class GatewayRoutesConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes().
                route(r->r
                        .path("/product/*")
                        .uri("lb://product-service")
                        .filters(new CustomGateway())
                        .id("api-product"))
                .build();
    }
}
