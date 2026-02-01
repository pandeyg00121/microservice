package com.gateway.microservice.api.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.common.MvcUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.web.servlet.function.RequestPredicates.path;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route("product_service")
                .route(path("/api/product/**"), HandlerFunctions.http())
                .before(request -> {
                    request.attributes().put(GATEWAY_REQUEST_URL_ATTR,
                            URI.create("http://localhost:8080" + request.path()));
                    return request;
                })
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions.route("order_service")
                .route(path("/api/order/**"), HandlerFunctions.http())
                .before(request -> {
                    request.attributes().put(GATEWAY_REQUEST_URL_ATTR,
                            URI.create("http://localhost:8081" + request.path()));
                    return request;
                })
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return GatewayRouterFunctions.route("inventory_service")
                .route(path("/api/inventory/**"), HandlerFunctions.http())
                .before(request -> {
                    request.attributes().put(GATEWAY_REQUEST_URL_ATTR,
                            URI.create("http://localhost:8082" + request.path()));
                    return request;
                })
                .build();
    }
}

