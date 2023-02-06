package com.example.assemblyvoting.service;

import com.example.assemblyvoting.model.Associado;
import com.example.assemblyvoting.model.DTO.AssociadoDTO;
import com.example.assemblyvoting.repository.AssociadoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration
class AssociadoServiceTest {

    @Mock
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


    @Test
    void existsById() {
        Mockito.when(associadoService.existsById(1L)).thenReturn(Mono.just(true));
        StepVerifier.create(associadoRepository.existsById(1L)).consumeNextWith(bool -> {
            assertEquals("should be true", bool, true);
        }).verifyComplete();
    }
}