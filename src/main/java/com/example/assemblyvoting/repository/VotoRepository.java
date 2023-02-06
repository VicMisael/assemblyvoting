package com.example.assemblyvoting.repository;

import com.example.assemblyvoting.model.DTO.VotoDTO;
import com.example.assemblyvoting.model.Voto;
import reactor.core.publisher.Mono;


public interface VotoRepository {


    /**
     * computa o voto.
     * @param voto O voto.
     * @return o Objeto completo de voto.
     */
    Mono<VotoDTO> save(Voto voto);

    /**
     * Cehca se o voto já foi computado
     * @param voto o voto
     * @return Se o voto já foi computado ou não
     */
    Mono<Boolean> checkRepeatedVote(Voto voto);


}
