package com.example.assemblyvoting.service;

import com.example.assemblyvoting.model.Pauta;
import com.example.assemblyvoting.model.Sessao;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public interface PautaService {


    public Mono<Pauta> insertPauta(Pauta pauta);

    public Mono<Pauta> iniciaSessao(Sessao sessao);

    public Flux<Pauta> getLatestPautas();

    public Mono<Pauta> getPautaById(Long id);
}
