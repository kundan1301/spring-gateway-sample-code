package com.example.demo.filters;

import com.example.demo.dataclass.BodyParser;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractChangeRequestUriGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;


@Component
public class BodySplitFilter extends AbstractChangeRequestUriGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {


    public BodySplitFilter() {
        super(NameConfig.class);
    }

    @Override
    protected Optional<URI> determineRequestUri(ServerWebExchange exchange, NameConfig config) {
        BodyParser bodyParser = exchange.getAttribute(ServerWebExchangeUtils.CACHED_REQUEST_BODY_ATTR);
        if(bodyParser.host != null && bodyParser.host.equals("google")) {
            try {
                return Optional.of(new URI("https://www.google.com/"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }
}

