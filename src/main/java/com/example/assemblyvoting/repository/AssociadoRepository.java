package com.example.assemblyvoting.repository;

import com.example.assemblyvoting.model.Associado;
import lombok.NonNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AssociadoRepository extends ReactiveSortingRepository<Associado, Long> {
}
