package com.example.assemblyvoting.service;

import com.example.assemblyvoting.model.EstadoSessao;
import com.example.assemblyvoting.model.Opcao;
import com.example.assemblyvoting.model.Pauta;
import com.example.assemblyvoting.model.Sessao;
import com.example.assemblyvoting.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration
class PautaServiceTest {


    @Mock
    static private PautaRepository pautaRepository;
    @InjectMocks
    static private PautaServiceImpl pautaService;

    @BeforeEach
    void setUp() {
    }


    @Test
    void insertPauta() {
        Pauta returnedPauta = Pauta.builder().nomePauta("PAUTA TESTE")
                .descricao("DESCRICAO")
                .id(1L)
                .build();

        Pauta insertedPauta = Pauta.builder().nomePauta("PAUTA TESTE")
                .descricao("DESCRICAO")
                .build();

        Mockito.when(pautaRepository.save(returnedPauta)).thenReturn(Mono.just(returnedPauta));

        Mono<Pauta> pauta = pautaService.insertPauta(returnedPauta);

        StepVerifier.create(pauta).consumeNextWith(pautaInTest -> {
            assertEquals("Nome da Pauta deve ser igual", pautaInTest.getNomePauta(), insertedPauta.getNomePauta());
            assertEquals("O ID deve ser igual a 1", pautaInTest.getId(), 1L);
        }).verifyComplete();


    }

    @Test
    void iniciaSessao() {
        final var now = LocalDateTime.now();
        final var nowplus1min = now.plus(1L, ChronoUnit.MINUTES);
        Pauta returnedPauta = Pauta.builder().nomePauta("PAUTA TESTE")
                .descricao("DESCRICAO")
                .id(1L)
                .build();
        Pauta returnedPautaWithTime = Pauta.builder().nomePauta("PAUTA TESTE")
                .descricao("DESCRICAO")
                .id(1L)
                .horarioInicio(now)
                .horarioTermino(nowplus1min)
                .build();

        Mockito.when(pautaRepository.findById(1L)).thenReturn(Mono.just(returnedPauta));
        Mockito.when(pautaRepository.save(returnedPauta)).thenReturn(Mono.just(returnedPautaWithTime));

        final Sessao sessao = Sessao.builder().pautaId(1L).horarioInicio(now).build();
        var pautaWithTime = pautaService.iniciaSessao(sessao);
        StepVerifier.create(pautaWithTime).consumeNextWith(pauta -> {
            assertEquals("Deve ter 1 minuto a mais", pauta.getHorarioTermino(), nowplus1min);
        }).verifyComplete();

    }

    @Test
    void getPautas() {
        final var now = LocalDateTime.now();
        final var nowplus1min = now.plus(1L, ChronoUnit.MINUTES);
        Pauta pauta = Pauta.builder().nomePauta("PAUTA TESTE")
                .descricao("DESCRICAO")
                .id(1L)
                .horarioInicio(now)
                .horarioTermino(nowplus1min)
                .build();
        Mockito.when(pautaRepository.findAll()).thenReturn(Flux.just(pauta));
        Flux<Pauta> pautaFlux = pautaService.getPautas();
        StepVerifier.create(pautaFlux).consumeNextWith(pauta1 -> {
            assertEquals("Id deve ser o mesmo", pauta1.getId(), 1L);
        }).thenConsumeWhile(pauta1 -> true).verifyComplete();

    }

    @Test
    void getPautaById() {
        Pauta pauta = Pauta.builder().nomePauta("PAUTA TESTE")
                .descricao("DESCRICAO")
                .id(1L)
                .build();
        Mockito.when(pautaRepository.findById(1L)).thenReturn(Mono.just(pauta));
        Mono<Pauta> pautaMono = pautaService.getPautaById(1L);
        StepVerifier.create(pautaMono).consumeNextWith(pauta1 -> {
            assertEquals("O nome deve ser igual", pauta1.getNomePauta(), pauta.getNomePauta());
        }).verifyComplete();
    }

    @Test
    void existsById() {
        Mockito.when(pautaRepository.existsById(1L)).thenReturn(Mono.just(Boolean.TRUE));
        Mono<Boolean> pautaMono = pautaService.existsById(1L);
        StepVerifier.create(pautaMono).consumeNextWith(exists -> {
            assertEquals("O nome deve ser igual", exists, Boolean.TRUE);
        }).verifyComplete();
    }

    @Test
    void getResultadoVotacao() {
        final var now = LocalDateTime.now();
        final var nowplus1min = now.plus(1L, ChronoUnit.MINUTES);
        Pauta pauta = Pauta.builder().nomePauta("PAUTA TESTE")
                .descricao("DESCRICAO")
                .id(1L)
                .horarioInicio(now)
                .horarioTermino(nowplus1min)
                .build();
        Map<Opcao, Integer> mapOpcoes = Map.of(Opcao.NAO, 50, Opcao.SIM, 65);
        Mockito.when(pautaRepository.findById(1L)).thenReturn(Mono.just(pauta));
        Mockito.when(pautaRepository.getVotacaoByPauta(1L)).thenReturn(Mono.just(mapOpcoes));
        final var resultadoVotacao = pautaService.getResultadoVotacao(1l);

        StepVerifier.create(resultadoVotacao).consumeNextWith(votacao -> {
            assertEquals("Numero de NAO deve ser igual", votacao.getVotosNao(), 50);
            assertEquals("Numero de NAO deve ser igual", votacao.getVotosSim(), 65);
            assertEquals("Nome da pauta deve ser igual", votacao.getPauta().getNomePauta(), pauta.getNomePauta());
            assertEquals("Descrição da pauta deve ser igual", votacao.getPauta().getNomePauta(), pauta.getNomePauta());
            assertEquals("O estado da sessão deve ser em Curso", votacao.getEstadoSessao(), EstadoSessao.EMCURSO);
            assertEquals("A descrição deve ser igual", votacao.getPauta().getDescricao(), pauta.getDescricao());
        }).verifyComplete();
    }

    @Test
    void checkEstadoSessaoTerminada() {
        final var now = LocalDateTime.now().minus(2, ChronoUnit.DAYS);
        final var nowplus1min = now.minus(1L, ChronoUnit.DAYS);
        Pauta pauta = Pauta.builder().nomePauta("PAUTA TESTE")
                .descricao("DESCRICAO")
                .id(1L)
                .horarioInicio(now)
                .horarioTermino(nowplus1min)
                .build();
        Map<Opcao, Integer> mapOpcoes = Map.of(Opcao.NAO, 50, Opcao.SIM, 65);
        Mockito.when(pautaRepository.findById(1L)).thenReturn(Mono.just(pauta));
        Mockito.when(pautaRepository.getVotacaoByPauta(1L)).thenReturn(Mono.just(mapOpcoes));
        final var resultadoVotacao = pautaService.getResultadoVotacao(1l);

        StepVerifier.create(resultadoVotacao).consumeNextWith(votacao -> {
            assertEquals("O estado da sessão deve ser em Curso", votacao.getEstadoSessao(), EstadoSessao.TERMINADA);
        }).verifyComplete();
    }

    @Test
    void checkEstadoSessaoNaoIniciado() {
        final var now = LocalDateTime.now().plus(1, ChronoUnit.DAYS);
        final var nowplus1min = now.plus(2, ChronoUnit.DAYS);
        Pauta pauta = Pauta.builder().nomePauta("PAUTA TESTE")
                .descricao("DESCRICAO")
                .id(1L)
                .horarioInicio(now)
                .horarioTermino(nowplus1min)
                .build();
        Map<Opcao, Integer> mapOpcoes = Map.of(Opcao.NAO, 50, Opcao.SIM, 65);
        Mockito.when(pautaRepository.findById(1L)).thenReturn(Mono.just(pauta));
        Mockito.when(pautaRepository.getVotacaoByPauta(1L)).thenReturn(Mono.just(mapOpcoes));
        final var resultadoVotacao = pautaService.getResultadoVotacao(1l);

        StepVerifier.create(resultadoVotacao).consumeNextWith(votacao -> {
            assertEquals("O estado da sessão deve ser nao iniciada", votacao.getEstadoSessao(), EstadoSessao.NAOINICIADA);
        }).verifyComplete();
    }

    @Test
    void checkEstadoSessaoNaoConfigurado() {
        Pauta pauta = Pauta.builder().nomePauta("PAUTA TESTE")
                .descricao("DESCRICAO")
                .id(1L)
                .build();
        Map<Opcao, Integer> mapOpcoes = new HashMap<>();
        Mockito.when(pautaRepository.findById(1L)).thenReturn(Mono.just(pauta));
        Mockito.when(pautaRepository.getVotacaoByPauta(1L)).thenReturn(Mono.just(mapOpcoes));
        final var resultadoVotacao = pautaService.getResultadoVotacao(1l);

        StepVerifier.create(resultadoVotacao).consumeNextWith(votacao -> {
            assertEquals("O estado da sessão deve ser nao configurada", votacao.getEstadoSessao(), EstadoSessao.NAO_CONFIGURADA);
            assertEquals("Os valores de sim e não devem ser 0s", votacao.getVotosNao(), 0);
            assertEquals("Os valores de sim e não devem ser 0s", votacao.getVotosSim(), 0);
        }).verifyComplete();
    }
}