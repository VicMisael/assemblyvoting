package com.example.assemblyvoting.service;

import com.example.assemblyvoting.model.Pauta;
import com.example.assemblyvoting.model.Sessao;
import com.example.assemblyvoting.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {
    private final PautaRepository pautaRepository;

    @Override
    public Mono<Pauta> insertPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    @Override
    public Mono<Pauta> iniciaSessao(Sessao sessao) {
        if (sessao.getHorarioTermino() == null) {
            sessao.setHorarioTermino(sessao.getHorarioInicio().plusMinutes(1));
        }
        return pautaRepository.findById(sessao.getPautaId()).flatMap(pauta -> {
            pauta.setHorarioInicio(sessao.getHorarioInicio());
            pauta.setHorarioTermino(sessao.getHorarioTermino());
            return pautaRepository.save(pauta);
        });
    }

    @Override
    public Flux<Pauta> getLatestPautas() {
        return pautaRepository.findAll();
    }

    @Override
    public Mono<Pauta> getPautaById(Long id) {
        return pautaRepository.findById(id);
    }
}
