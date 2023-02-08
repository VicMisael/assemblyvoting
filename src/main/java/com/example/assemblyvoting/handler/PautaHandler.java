package com.example.assemblyvoting.handler;

import com.example.assemblyvoting.exception.PautaInexistenteException;
import com.example.assemblyvoting.model.DTO.PautaDTO;
import com.example.assemblyvoting.model.Pauta;
import com.example.assemblyvoting.model.Sessao;
import com.example.assemblyvoting.service.PautaService;
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
public class PautaHandler {
    private final PautaService pautaService;

    public Mono<ServerResponse> insertPauta(ServerRequest serverRequest) {
        log.debug("PautaHandler.insertPauta");
        return serverRequest.bodyToMono(PautaDTO.class).flatMap(pautaDTO -> pautaService.insertPauta(pautaDTO.toPauta())
                        .flatMap(pauta -> created(URI.create("/pauta/" + pauta.getId())).contentType(MediaType.APPLICATION_JSON).bodyValue(pauta)))
                .switchIfEmpty(badRequest().build());
    }

    public Mono<ServerResponse> iniciaSessao(ServerRequest serverRequest) {
        log.debug("PautaHandler.iniciaSessao");
        return serverRequest.bodyToMono(Sessao.class).flatMap(sessao ->
                        pautaService.iniciaSessao(sessao).flatMap(pauta -> ok().contentType(MediaType.APPLICATION_JSON).bodyValue(pauta)))
                .switchIfEmpty(Mono.error(PautaInexistenteException::new));
    }

    public Mono<ServerResponse> getPautas(ServerRequest serverRequest) {
        log.debug("PautaHandler.getPautaById");
        return ok().contentType(MediaType.APPLICATION_JSON).body(pautaService.getPautas(), Pauta.class);
    }

    public Mono<ServerResponse> getPautaById(ServerRequest serverRequest) {
        log.debug("PautaHandler.getPautaById");
        Long id = Long.valueOf(serverRequest.pathVariable("id"));
        return pautaService.getPautaById(id)
                .flatMap(pauta -> ok().contentType(MediaType.APPLICATION_JSON).bodyValue(pauta))
                .switchIfEmpty(
                        notFound().build()
                );
    }

    public Mono<ServerResponse> getVotacaoByPautaId(ServerRequest serverRequest) {
        log.debug("PautaHandler.getVotacaoByPautaId");
        return Mono.just(Long.valueOf(serverRequest.pathVariable("id"))).flatMap(id ->
                pautaService.getResultadoVotacao(id).flatMap(votacao -> ok().bodyValue(votacao)).switchIfEmpty(notFound().build())
        ).onErrorMap(NumberFormatException.class, e -> e);

    }


}
