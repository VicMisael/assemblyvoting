package com.example.assemblyvoting.handler.router;

import com.example.assemblyvoting.handler.VotoHandler;
import com.example.assemblyvoting.model.DTO.VotoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@RequiredArgsConstructor
@Component
public class VotoHandlerRouter {

    @Bean
    @RouterOperation(path = "/voto", produces = {
            MediaType.APPLICATION_JSON_VALUE}
            , operation = @Operation(operationId = "registrarVoto", responses = {
            @ApiResponse(responseCode = "200", description = "Voto Registrado com sucesso", content = @Content(schema = @Schema(implementation = VotoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Pauta ou Associado não encontrados, ou voto duplicado"),
    }
            , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = VotoDTO.class)))), method = RequestMethod.POST)
    public RouterFunction<ServerResponse> votoRoutes(final VotoHandler votoHandler) {
        return route().path("/voto", builder -> builder
                .POST(votoHandler::registrarVoto)
        ).build();
    }
}
