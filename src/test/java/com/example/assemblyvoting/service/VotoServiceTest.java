package com.example.assemblyvoting.service;

import com.example.assemblyvoting.model.DTO.VotoDTO;
import com.example.assemblyvoting.model.Opcao;
import com.example.assemblyvoting.model.Pauta;
import com.example.assemblyvoting.model.Voto;
import com.example.assemblyvoting.repository.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
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

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration
class VotoServiceTest {

    @Mock
    private static VotoRepository votoRepository;

    @Mock
    private static AssociadoService associadoService;

    @Mock
    private static PautaService pautaService;

    @InjectMocks
    private static VotoServiceImpl votoService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void registrarVoto() {
        final var now = LocalDateTime.now();
        final var nowplus1min = now.plus(1L, ChronoUnit.MINUTES);

        Pauta returnedPautaWithTime = Pauta.builder().nomePauta("PAUTA TESTE")
                .descricao("DESCRICAO")
                .id(1L)
                .horarioInicio(now)
                .horarioTermino(nowplus1min)
                .build();

        Voto voto = Voto.builder().pautaId(1L).opcao(Opcao.SIM).associadoId(1L).build();
        Mockito.when(pautaService.getPautaById(1L)).thenReturn(Mono.just(returnedPautaWithTime));
        Mockito.when(associadoService.existsById(1L)).thenReturn(Mono.just(true));
        VotoDTO votoDTO = VotoDTO.builder().pauta(returnedPautaWithTime).opcao(Opcao.SIM).id(1L).build();
        Mockito.when(votoRepository.save(voto)).thenReturn(Mono.just(votoDTO));
        StepVerifier.create(votoService.registrarVoto(voto)).consumeNextWith(votoDTO1 -> {
            assertEquals("Voto ", votoDTO1.getOpcao(), Opcao.SIM);
        }).verifyComplete();


    }
}