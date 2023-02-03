package com.example.assemblyvoting.service;

import com.example.assemblyvoting.model.Associado;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AssociadoService {
     Mono<Associado> insertAssociado(Associado associado);

     Flux<Associado> getAssociados();

     Mono<Associado> getAssociadoById(Long id);

    Mono<Boolean> existsById(Long id);
}
