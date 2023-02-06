package com.example.assemblyvoting.handler.router;

import com.example.assemblyvoting.handler.VotoHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@RequiredArgsConstructor
@Component
public class VotoHandlerRouter implements HandlerRouter {

    private final VotoHandler votoHandler;


    @Override
    public RouterFunction<ServerResponse> getRoutes() {
        return route().path("/voto", builder -> builder
                .POST(votoHandler::registrarVoto)
        ).build();
    }
}
