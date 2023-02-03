package com.example.assemblyvoting.service;

import com.example.assemblyvoting.exception.HorarioDeSessaoInvalidoException;
import com.example.assemblyvoting.model.Opcao;
import com.example.assemblyvoting.model.Pauta;
import com.example.assemblyvoting.model.Sessao;
import com.example.assemblyvoting.model.Votacao;
import com.example.assemblyvoting.repository.PautaRepository;
import com.example.assemblyvoting.util.EstadoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

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
        if (sessao.getHorarioFim() == null) {
            sessao.setHorarioFim(sessao.getHorarioInicio().plusMinutes(1));
        } else if (sessao.getHorarioFim().isBefore(sessao.getHorarioInicio())) {
            return Mono.error(new HorarioDeSessaoInvalidoException());
        }
        return pautaRepository.findById(sessao.getPautaId()).flatMap(pauta -> {
            pauta.setHorarioInicio(sessao.getHorarioInicio());
            pauta.setHorarioTermino(sessao.getHorarioFim());
            return pautaRepository.save(pauta);
        });
    }

    @Override
    public Flux<Pauta> getPautas() {
        return pautaRepository.findAll();
    }

    @Override
    public Mono<Pauta> getPautaById(Long id) {
        return pautaRepository.findById(id);
    }

    @Override
    public Mono<Boolean> existsById(Long pautaId) {
        return pautaRepository.existsById(pautaId);
    }

    @Override
    public Mono<Votacao> getResultadoVotacao(Long pautaId) {
        return Mono.zip(getPautaById(pautaId), pautaRepository.getVotacaoByPauta(pautaId))
                .map(tuple -> Votacao.builder()
                        .pauta(tuple.getT1())
                        .votosSim(tuple.getT2().get(Opcao.SIM))
                        .votosNao(tuple.getT2().get(Opcao.NAO))
                        .build());
    }


}
