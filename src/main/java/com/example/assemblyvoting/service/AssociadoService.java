package com.example.assemblyvoting.service;

import com.example.assemblyvoting.model.Associado;
import lombok.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AssociadoService {
    public Mono<Associado> insertAssociado(Associado associado);

    public Flux<Associado> getAssociados();

    public Mono<Associado> getAssociadoById(Long id);
}
