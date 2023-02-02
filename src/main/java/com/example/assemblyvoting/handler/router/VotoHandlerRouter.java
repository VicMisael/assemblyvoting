package com.example.assemblyvoting.handler.router;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@RequiredArgsConstructor
public class VotoHandlerRouter implements HandlerRouter {

    @Override
    public RouterFunction<ServerResponse> getRoutes() {
        return null;
    }
}
