package com.jaclon.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jaclon
 * @date 2020/4/12
 */
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder builder = routeLocatorBuilder.routes();
        return builder.route("path_route_jaclon",r -> r.path("/guonei").uri("https://news.baidu.com/guonei")).build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        return routes.route("path_route_jaclon2", r -> r.path("/guoji").uri("https://news.baidu.com/guoji")).build();
    }
}
