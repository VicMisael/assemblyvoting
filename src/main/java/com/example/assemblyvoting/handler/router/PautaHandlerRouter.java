package com.example.assemblyvoting.handler.router;

import com.example.assemblyvoting.handler.PautaHandler;
import com.example.assemblyvoting.model.DTO.PautaDTO;
import com.example.assemblyvoting.model.Pauta;
import com.example.assemblyvoting.model.Sessao;
import com.example.assemblyvoting.model.Votacao;
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
public class PautaHandlerRouter {


    @Bean
    @RouterOperations({
            @RouterOperation(path = "/pauta/resultado/{id}", produces = {
                    MediaType.APPLICATION_JSON_VALUE}
                    , operation = @Operation(operationId = "getVotacaoByPautaId", responses = {
                    @ApiResponse(responseCode = "200", description = "buscado com sucesso", content = @Content(schema = @Schema(implementation = Votacao.class))),
                    @ApiResponse(responseCode = "400", description = "Erro ao buscar"),
                    @ApiResponse(responseCode = "404", description = "não encontrado")
            }
                    , parameters = {@Parameter(in = ParameterIn.PATH, name = "id da pauta")}), method = RequestMethod.GET),

            @RouterOperation(path = "/pauta/{id}", produces = {
                    MediaType.APPLICATION_JSON_VALUE}
                    , operation = @Operation(operationId = "getVotacaoByPautaId", responses = {
                    @ApiResponse(responseCode = "200", description = "buscado com sucesso", content = @Content(schema = @Schema(implementation = Pauta.class))),
                    @ApiResponse(responseCode = "400", description = "Erro ao buscar"),
                    @ApiResponse(responseCode = "404", description = "não encontrado")
            }
                    , parameters = {@Parameter(in = ParameterIn.PATH, name = "id da pauta")}), method = RequestMethod.GET),

            @RouterOperation(path = "/pauta/", produces = {
                    MediaType.APPLICATION_JSON_VALUE}
                    , operation = @Operation(operationId = "getVotacaoByPautaId", responses = {
                    @ApiResponse(responseCode = "200", description = "buscado com sucesso", content = @Content(schema = @Schema(implementation = Pauta.class))),
            }), method = RequestMethod.GET),

            @RouterOperation(path = "/pauta/sessao", produces = {
                    MediaType.APPLICATION_JSON_VALUE}
                    , operation = @Operation(operationId = "iniciaSessao", responses = {
                    @ApiResponse(responseCode = "200", description = "sessao cadastrada", content = @Content(schema = @Schema(implementation = Sessao.class))),
                    @ApiResponse(responseCode = "404", description = "Pauta inexistente"),
            }
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Sessao.class)))), method = RequestMethod.POST),
            @RouterOperation(path = "/pauta/", produces = {
                    MediaType.APPLICATION_JSON_VALUE}
                    , operation = @Operation(operationId = "cadastraPauta", responses = {
                    @ApiResponse(responseCode = "200", description = "pauta registrada com sucesso", content = @Content(schema = @Schema(implementation = Pauta.class))),
                    @ApiResponse(responseCode = "400", description = "Probelema ao cadastrar"),
            }
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = PautaDTO.class)))), method = RequestMethod.POST)
    })
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
