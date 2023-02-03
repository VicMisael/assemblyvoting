package com.example.assemblyvoting.handler;

import com.example.assemblyvoting.model.Associado;
import com.example.assemblyvoting.model.DTO.PautaDTO;
import com.example.assemblyvoting.model.Pauta;
import com.example.assemblyvoting.model.Sessao;
import com.example.assemblyvoting.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
@RequiredArgsConstructor
public class PautaHandler {
    private final PautaService pautaService;

    public Mono<ServerResponse> insertPauta(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(PautaDTO.class).flatMap(pautaDTO -> pautaService.insertPauta(pautaDTO.toPauta())
                .flatMap(pauta -> created(URI.create("/pauta/" + pauta.getId())).contentType(MediaType.APPLICATION_JSON).bodyValue(pauta)));
    }

    public Mono<ServerResponse> iniciaSessao(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Sessao.class).flatMap(sessao ->
                pautaService.iniciaSessao(sessao).flatMap(pauta -> ok().contentType(MediaType.APPLICATION_JSON).bodyValue(pauta)));
    }

    public Mono<ServerResponse> getPautas(ServerRequest serverRequest) {
        var pautas = pautaService.getLatestPautas();
        return ok().contentType(MediaType.APPLICATION_JSON).body(pautas, Pauta.class);
    }

    public Mono<ServerResponse> getPauta(ServerRequest serverRequest) {
        Long id = Long.valueOf(serverRequest.pathVariable("id"));
        return pautaService.getPautaById(id)
                .flatMap(pauta -> ok().contentType(MediaType.APPLICATION_JSON).body(pauta, Pauta.class))
                .switchIfEmpty(
                        notFound().build()
                );
    }


}