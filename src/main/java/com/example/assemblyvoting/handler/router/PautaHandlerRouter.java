package com.example.assemblyvoting.handler.router;

import com.example.assemblyvoting.handler.PautaHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@RequiredArgsConstructor
public class PautaHandlerRouter  {


    @Bean
    public RouterFunction<ServerResponse> pautaRoutes(final PautaHandler pautaHandler) {
        return route().path("/pauta", builder -> builder
                .GET("/resultado/{id}", pautaHandler::getVotacaoByPautaId)
                .GET("/{id}", pautaHandler::getPautaById)
                .GET(pautaHandler::getPautas)
                .POST("/sessao", pautaHandler::iniciaSessao)
                .POST(pautaHandler::insertPauta)
        ).build();
    }
}
