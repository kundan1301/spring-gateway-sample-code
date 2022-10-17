package com.example.demo;

import com.example.demo.dataclass.BodyParser;
import com.example.demo.filters.BodySplitFilter;
import com.example.demo.filters.SplitGetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.util.HashMap;

@Configuration
public class Routes {

    @Autowired
    SplitGetFilter splitGetFilter;

    @Autowired
    BodySplitFilter bodySplitFilter;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("get_route", r -> r.path("/get/**").and().method(HttpMethod.GET)
                        .filters(f->f.filter(splitGetFilter.apply(new AbstractGatewayFilterFactory.NameConfig())))
                        .uri("https://httpbin.org"))
                .route("post_body",r->r.path("/post/**").and().method(HttpMethod.POST)
                        .filters(f->f.cacheRequestBody(BodyParser.class).filter(bodySplitFilter.apply(new AbstractGatewayFilterFactory.NameConfig())))
                        .uri("https://httpbin.org/"))
                .route("default_route", r->r.alwaysTrue().uri("https://httpbin.org"))
                .build();
    }

}
