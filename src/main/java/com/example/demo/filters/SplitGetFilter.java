package com.example.demo.filters;

import org.springframework.cloud.gateway.filter.factory.AbstractChangeRequestUriGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Random;


@Component
public class SplitGetFilter extends AbstractChangeRequestUriGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {
    private Random random;
    public SplitGetFilter() {
        super(NameConfig.class);
        random=new Random();
    }

    @Override
    protected Optional<URI> determineRequestUri(ServerWebExchange exchange, NameConfig config) {
        //URI requestUrl = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        //Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        int i = random.nextInt(100);
        if(i<=50) {
            try {
                return Optional.of(new URI("https://www.google.com/"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }
}
