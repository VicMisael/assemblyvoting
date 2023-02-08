package com.example.assemblyvoting.handler;

import com.example.assemblyvoting.model.Associado;
import com.example.assemblyvoting.model.DTO.AssociadoDTO;
import com.example.assemblyvoting.service.AssociadoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class AssociadoHandler {

    private final AssociadoService associadoService;

    public Mono<ServerResponse> insertAssociado(ServerRequest serverRequest) {
        log.debug("AssociadoHandler.insertAssociado");
        return serverRequest.bodyToMono(AssociadoDTO.class)
                .flatMap(associadoDTO -> associadoService.insertAssociado(associadoDTO.toAssociado())
                        .flatMap(associado -> created(URI.create("/associado/" + associado.getAssociadoId()))
                                .contentType(MediaType.APPLICATION_JSON).bodyValue(associado))).switchIfEmpty(badRequest().build());
    }

    public Mono<ServerResponse> getAssociados(ServerRequest serverRequest) {
        log.debug("AssociadoHandler.getAssociados");
        return ok().contentType(MediaType.APPLICATION_JSON).body(associadoService.getAssociados(), Associado.class);
    }

    public Mono<ServerResponse> getAssociadoById(ServerRequest serverRequest) {
        log.debug("AssociadoHandler.getAssociadoById");
        return Mono.just(Long.valueOf(serverRequest.pathVariable("id"))).flatMap(id -> associadoService.getAssociadoById(Long.valueOf(serverRequest.pathVariable("id")))
                        .flatMap(body ->
                                ok().contentType(MediaType.APPLICATION_JSON).bodyValue(body))
                        .switchIfEmpty(notFound().build()))
                .onErrorMap(NumberFormatException.class, e -> e);
    }
}
