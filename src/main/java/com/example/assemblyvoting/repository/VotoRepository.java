package com.example.assemblyvoting.repository;

import com.example.assemblyvoting.model.Associado;
import com.example.assemblyvoting.model.DTO.VotoDTO;
import com.example.assemblyvoting.model.Pauta;
import com.example.assemblyvoting.model.Voto;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;


public interface VotoRepository {

    /**
     * Retorna pauta e associados a partir dos Ids fornecidos em Voto, processa em uma só consulta
     *
     * @return Tupla contendo ambos, Caso um ou outro não exista, retorna um MonoVazio
     */
    Mono<Tuple2<Pauta, Associado>> findPautaAndAssociadoByPautaIdAndAssociadoId(final Voto voto);

    /**
     * computa o voto.
     *
     * @param voto O voto.
     * @return o Objeto completo de voto.
     */
    Mono<VotoDTO> save(Voto voto);

    /**
     * Cehca se o voto já foi computado
     *
     * @param voto o voto
     * @return Se o voto já foi computado ou não
     */
    Mono<Boolean> checkRepeatedVote(Voto voto);


}
