package com.example.assemblyvoting.handler.router;

import com.example.assemblyvoting.handler.AssociadoHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@RequiredArgsConstructor
public class AssociadoHandlerRouter {

    @Bean
    public RouterFunction<ServerResponse> associadoRoutes(final AssociadoHandler associadoHandler) {
        return route().path("/associado", builder -> builder
                .GET("/{id}", associadoHandler::getAssociadoById)
                .GET(associadoHandler::getAssociados)
                .POST(associadoHandler::insertAssociado)).build();
    }
}
