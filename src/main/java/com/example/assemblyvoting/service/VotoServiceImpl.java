package com.example.assemblyvoting.service;

import com.example.assemblyvoting.exception.PautaOuAssociadoInexistentes;
import com.example.assemblyvoting.exception.VotoDuplicadoException;
import com.example.assemblyvoting.model.DTO.VotoDTO;
import com.example.assemblyvoting.model.EstadoSessao;
import com.example.assemblyvoting.model.Pauta;
import com.example.assemblyvoting.model.Voto;
import com.example.assemblyvoting.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;

import static com.example.assemblyvoting.util.EstadoUtils.checkEstadoSessao;

@Service
@RequiredArgsConstructor
public class VotoServiceImpl implements VotoService {
    private final VotoRepository votoRepository;

    @Override
    public Mono<VotoDTO> registrarVoto(Voto voto) {
        return votoRepository.findPautaAndAssociadoByPautaIdAndAssociadoId(voto).flatMap(tuple -> {
            final Pauta pauta = tuple.getT1();
            EstadoSessao estadoSessao = checkEstadoSessao(pauta.getHorarioInicio(), pauta.getHorarioTermino());
            if (estadoSessao == EstadoSessao.EMCURSO) {
                return votoRepository.save(voto);
            }
            try {
                return Mono.error(estadoSessao.getHttpException().getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                return Mono.error(new RuntimeException(e));
            }
        }).switchIfEmpty(Mono.error(new PautaOuAssociadoInexistentes())).onErrorMap(DataIntegrityViolationException.class, throwable -> {
            throw new VotoDuplicadoException();
        });

    }


}
