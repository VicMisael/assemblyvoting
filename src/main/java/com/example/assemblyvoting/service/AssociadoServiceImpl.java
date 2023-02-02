package com.example.assemblyvoting.service;

import com.example.assemblyvoting.model.Associado;
import com.example.assemblyvoting.repository.AssociadoRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class AssociadoServiceImpl implements AssociadoService {
    private final AssociadoRepository associadoRepository;

    @Override
    public Mono<Associado> insertAssociado(Associado associado) {
        return associadoRepository.save(associado);
    }

    @Override
    public Flux<Associado> getAssociados() {
        return associadoRepository.findAll();
    }

    @Override
    public Mono<Associado> getAssociadoById(Long id) {
        return associadoRepository.findById(id);
    }

}
