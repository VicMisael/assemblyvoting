package com.example.assemblyvoting.repository;

import com.example.assemblyvoting.model.Associado;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface AssociadoRepository extends ReactiveSortingRepository<Associado, Long> {

}
