package com.example.assemblyvoting.service;

import com.example.assemblyvoting.model.Pauta;
import com.example.assemblyvoting.model.Sessao;
import com.example.assemblyvoting.model.Votacao;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface PautaService {
     Mono<Pauta> insertPauta(Pauta pauta);

     Mono<Pauta> iniciaSessao(Sessao sessao);

     Flux<Pauta> getLatestPautas();

     Mono<Pauta> getPautaById(Long id);

     Mono<Boolean> existsById(Long pautaID);

     Mono<Votacao> getResultadoVotacao(Long pautaId);
}
