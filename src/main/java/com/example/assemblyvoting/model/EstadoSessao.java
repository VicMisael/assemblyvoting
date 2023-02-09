package com.example.assemblyvoting.model;

import com.example.assemblyvoting.exception.*;
import lombok.Getter;
import org.springframework.web.server.ResponseStatusException;

@Getter
public enum EstadoSessao {
    NAOINICIADA(VotacaoNaoIniciadaException.class), EMCURSO(DesconhecidoException.class), TERMINADA(VotacaoEncerradaException.class),
    NAO_CONFIGURADA(SemDadosSessaoException.class);
    private final Class<? extends ResponseStatusException> httpException;

    EstadoSessao(Class<? extends ResponseStatusException> httpException) {
        this.httpException = httpException;
    }

}