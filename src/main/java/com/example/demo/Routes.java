package com.example.demo;

import com.example.demo.filters.SplitGetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class Routes {

    @Autowired
    SplitGetFilter splitGetFilter;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("get_route", r -> r.path("/get/**").and().method(HttpMethod.GET)
                        .filters(f->f.filter(splitGetFilter.apply(new AbstractGatewayFilterFactory.NameConfig()))).uri("https://httpbin.org"))
                .route("default_route", r->r.alwaysTrue().uri("https://httpbin.org"))
                .build();
    }

}
