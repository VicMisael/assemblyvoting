package com.example.assemblyvoting.repository;

import com.example.assemblyvoting.model.Opcao;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface PautaCustomRepository {

    /**
     * Recebe o Id da pauta escolhida, e retorna o número de votos de cada opção.
     *
     * @param pautaId:Id da pauta com os dados da votação.
     * @return Um map contendo o número de votos para cada opção.
     */
    Mono<Map<Opcao, Integer>> getVotacaoByPauta(Long pautaId);
}
