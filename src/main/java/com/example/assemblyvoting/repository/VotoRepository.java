package com.example.assemblyvoting.repository;

import com.example.assemblyvoting.model.DTO.VotoDTO;
import com.example.assemblyvoting.model.Voto;
import reactor.core.publisher.Mono;


public interface VotoRepository {

    Mono<VotoDTO> save(Voto voto);

    Mono<Boolean> checkRepeatedVote(Voto voto);


}
