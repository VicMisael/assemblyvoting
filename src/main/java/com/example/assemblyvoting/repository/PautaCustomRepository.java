package com.example.assemblyvoting.repository;

import com.example.assemblyvoting.model.Opcao;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface PautaCustomRepository {
    Mono<Map<Opcao, Integer>> getVotacaoByPauta(Long pautaId);
}
