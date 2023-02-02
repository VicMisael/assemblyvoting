package com.example.assemblyvoting.handler.router;

import com.example.assemblyvoting.handler.AssociadoHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@RequiredArgsConstructor
public class AssociadoHandlerRouter implements HandlerRouter {
    private final AssociadoHandler associadoHandler;

    @Override
    public RouterFunction<ServerResponse> getRoutes() {
        return route().path("/associado", builder -> builder
                .GET("/{id}",associadoHandler::getAssociadoById)
                .GET(associadoHandler::getAssociados)
                .POST(associadoHandler::insertAssociado)).build();
    }
}
