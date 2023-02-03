package com.example.assemblyvoting.service;

import com.example.assemblyvoting.exception.AssociadoInexistenteException;
import com.example.assemblyvoting.exception.PautaInexistenteException;
import com.example.assemblyvoting.exception.VotoDuplicadoException;
import com.example.assemblyvoting.model.DTO.VotoDTO;
import com.example.assemblyvoting.model.EstadoSessao;
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
    private final AssociadoService associadoService;
    private final PautaService pautaService;

    @Override
    public Mono<VotoDTO> registrarVoto(Voto voto) {
        return checkIfAssociadoExists(voto.getAssociadoId())
                .flatMap(exists -> {
                            if (exists) {
                                return pautaService.getPautaById(voto.getPautaId()).flatMap(
                                        pauta -> {
                                            EstadoSessao estadoSessao = checkEstadoSessao(pauta.getHorarioInicio(), pauta.getHorarioTermino());
                                            if (estadoSessao == EstadoSessao.EMCURSO) {
                                                return votoRepository.save(voto);
                                            }
                                            try {
                                                return Mono.error(estadoSessao.getHttpException().getDeclaredConstructor().newInstance());
                                            } catch (InstantiationException | IllegalAccessException |
                                                     NoSuchMethodException | InvocationTargetException e) {
                                                return Mono.error(e);
                                            }
                                        }
                                ).switchIfEmpty(Mono.error(new PautaInexistenteException()));
                            }
                            return Mono.error(new AssociadoInexistenteException());
                        }
                ).onErrorMap(DataIntegrityViolationException.class, throwable -> {
                    throw new VotoDuplicadoException();
                });
    }


    private Mono<Boolean> checkIfAssociadoExists(Long associadoId) {
        return associadoService.existsById(associadoId);
    }


}
