package com.example.assemblyvoting.handler.router;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

public interface HandlerRouter {
    public RouterFunction<ServerResponse> getRoutes();
}
