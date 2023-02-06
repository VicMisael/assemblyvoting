package com.example.assemblyvoting.handler;

import com.example.assemblyvoting.model.Voto;
import com.example.assemblyvoting.service.VotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
@Slf4j
public class VotoHandler {

    private final VotoService votoService;

    public Mono<ServerResponse> registrarVoto(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Voto.class).flatMap(votoService::registrarVoto)
                .flatMap(votoDTO -> ok().bodyValue(votoDTO))
                .switchIfEmpty(notFound().build());
    }

}
