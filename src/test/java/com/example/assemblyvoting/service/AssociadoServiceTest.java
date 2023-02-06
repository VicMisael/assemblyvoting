package com.example.assemblyvoting.service;

import com.example.assemblyvoting.model.Associado;
import com.example.assemblyvoting.model.DTO.AssociadoDTO;
import com.example.assemblyvoting.repository.AssociadoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.springframework.test.util.AssertionErrors.assertEquals;

class AssociadoServiceTest {

    @MockBean
    AssociadoRepository associadoRepository;

    @InjectMocks
    AssociadoServiceImpl associadoService;
    static
    Associado associado;

    @BeforeAll
    static void setUp() {
        associado = AssociadoDTO.builder().nome("Associado").build().toAssociado();
    }


    @Test
    void insertAssociado() {
        var associadoWithId = AssociadoDTO.builder().nome("Associado").build().toAssociado();
        associadoWithId.setAssociadoId(1L);
        Mockito.when(associadoRepository.save(associado)).thenReturn(Mono.just(associadoWithId));

        Mono<Associado> associadoMono = associadoService.insertAssociado(associado);

        StepVerifier.create(associadoMono).consumeNextWith(associado -> {
            assertEquals("Nome do associado ", associado.getNome(), "Associado");
            assertEquals("ID associado", associado.getAssociadoId(), 1L);
        }).verifyComplete();
    }

}