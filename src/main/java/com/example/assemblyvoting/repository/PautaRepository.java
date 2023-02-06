package com.example.assemblyvoting.repository;

import com.example.assemblyvoting.model.Pauta;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface PautaRepository extends PautaCustomRepository,ReactiveSortingRepository<Pauta, Long> {
}
