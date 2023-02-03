package com.example.assemblyvoting.service;

import com.example.assemblyvoting.model.DTO.VotoDTO;
import com.example.assemblyvoting.model.Voto;
import reactor.core.publisher.Mono;

public interface VotoService {
    /**
     * checa a validade e computa o voto.
     *
     * @param voto O voto.
     * @return o Objeto completo de voto.
     */
    Mono<VotoDTO> registrarVoto(Voto voto);

}
