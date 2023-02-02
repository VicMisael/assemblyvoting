package com.example.assemblyvoting.handler.router;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class RootHandlerRouter implements HandlerRouter {
    @Override
    public RouterFunction<ServerResponse> getRoutes() {
        return route().GET("/", serverRequest -> ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN).
                bodyValue("ASSEMBLY VOTING")).build();
    }
}
