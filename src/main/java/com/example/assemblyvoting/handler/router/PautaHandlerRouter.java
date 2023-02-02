package com.example.assemblyvoting.handler.router;

import com.example.assemblyvoting.handler.PautaHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@RequiredArgsConstructor
public class PautaHandlerRouter implements HandlerRouter {

    private final PautaHandler pautaHandler;

    @Override
    public RouterFunction<ServerResponse> getRoutes() {
        return route().path("/pauta", builder -> builder
                .GET(pautaHandler::getPautas)
                .GET("/{id}", pautaHandler::getPauta)
                .POST("/sessao", pautaHandler::iniciaSessao)
                .POST(pautaHandler::insertPauta)
        ).build();
    }
}
