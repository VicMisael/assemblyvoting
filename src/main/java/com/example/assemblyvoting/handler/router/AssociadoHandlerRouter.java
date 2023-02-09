package com.example.assemblyvoting.handler.router;

import com.example.assemblyvoting.handler.AssociadoHandler;
import com.example.assemblyvoting.model.Associado;
import com.example.assemblyvoting.model.DTO.AssociadoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@RequiredArgsConstructor
public class AssociadoHandlerRouter {

    @Bean
    @RouterOperations(
            {
                    @RouterOperation(path = "/associado/{id}", produces = {
                            MediaType.APPLICATION_JSON_VALUE}
                            , operation = @Operation(operationId = "getAssociadoById", responses = {
                            @ApiResponse(responseCode = "200", description = "buscado com sucesso", content = @Content(schema = @Schema(implementation = Associado.class))),
                            @ApiResponse(responseCode = "400", description = "Erro ao buscar"),
                            @ApiResponse(responseCode = "404", description = "não encontrado")
                    }
                            , parameters = {@Parameter(in = ParameterIn.PATH, name = "associadoID")}), method = RequestMethod.GET),

                    @RouterOperation(path = "/associado", produces = {
                            MediaType.APPLICATION_JSON_VALUE}
                            , operation = @Operation(operationId = "getAssociados", responses = {
                            @ApiResponse(responseCode = "200", description = "Voto Registrado com sucesso", content = @Content(schema = @Schema(implementation = Associado.class))),

                    }), method = RequestMethod.GET),
                    @RouterOperation(path = "/associado", produces = {
                            MediaType.APPLICATION_JSON_VALUE}
                            , operation = @Operation(operationId = "cadastraAssociado", responses = {
                            @ApiResponse(responseCode = "200", description = "Voto Registrado com sucesso", content = @Content(schema = @Schema(implementation = Associado.class))),
                            @ApiResponse(responseCode = "400", description = "Pauta ou Associado não encontrados, ou voto duplicado"),
                    }
                            , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = AssociadoDTO.class)))), method = RequestMethod.POST)
            }
    )
    public RouterFunction<ServerResponse> associadoRoutes(final AssociadoHandler associadoHandler) {
        return route().path("/associado", builder -> builder
                .GET("/{id}", associadoHandler::getAssociadoById)
                .GET(associadoHandler::getAssociados)
                .POST(associadoHandler::insertAssociado)).build();
    }
}
