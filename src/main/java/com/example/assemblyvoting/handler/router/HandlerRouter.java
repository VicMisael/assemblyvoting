package com.example.assemblyvoting.handler.router;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
public interface HandlerRouter {
    RouterFunction<ServerResponse> getRoutes();
}
