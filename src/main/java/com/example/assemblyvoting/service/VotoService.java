package com.example.assemblyvoting.service;

import com.example.assemblyvoting.model.DTO.VotoDTO;
import com.example.assemblyvoting.model.Voto;
import reactor.core.publisher.Mono;

public interface VotoService {
    Mono<VotoDTO> registrarVoto(Voto voto);

}
