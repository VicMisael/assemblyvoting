package com.example.assemblyvoting.model;

import com.example.assemblyvoting.exception.*;
import lombok.Getter;

@Getter
public enum EstadoSessao {
    NAOINICIADA(VotacaoNaoIniciadaException.class), EMCURSO(DesconhecidoException.class), TERMINADA(VotacaoEncerradaException.class),
    NAO_CONFIGURADA(SemDadosSessaoException.class);
    private final Class<? extends HttpException> httpException;

    EstadoSessao(Class<? extends HttpException> httpException) {
        this.httpException = httpException;
    }

}