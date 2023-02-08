package com.example.assemblyvoting.config;

import com.example.assemblyvoting.handler.router.HandlerRouter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@Slf4j
public class Router {
    private final List<HandlerRouter> routers;

    public Router(HandlerRouter... handlerRouters) {
        routers = Arrays.asList(handlerRouters);
    }

    @Bean
    public RouterFunction<ServerResponse> routes() {
        var routes = route();
        routers.forEach(handler -> {
            log.info("Registering handler router" + handler.getClass().getName());
            routes.add(handler.getRoutes());
        });
        log.info("Routes registered");
        return routes.build();
    }
}
