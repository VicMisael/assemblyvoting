package com.example.assemblyvoting.service;

import com.example.assemblyvoting.model.Associado;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AssociadoService {
    /**
     * Persiste o associado.
     * @param associado O associado.
     * @return O associado com seu Id.
     */
     Mono<Associado> insertAssociado(Associado associado);

    /**
     * Retorna os Associados.
     * @return A lista de associados.
     */
     Flux<Associado> getAssociados();

    /**
     * Retorna o Associado por Id
     * @param id id do Associado
     * @return O associado, caso seja encontrado, Vazio caso contrário.
     */
     Mono<Associado> getAssociadoById(Long id);

    /**
     * Checa se o associado existe.
     * @param id Id do associado
     * @return Verdadeiro caso o associado exista, falso caso contrário
     */
    Mono<Boolean> existsById(Long id);
}
