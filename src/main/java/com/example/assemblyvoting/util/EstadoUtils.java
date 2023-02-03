package com.example.assemblyvoting.util;

import com.example.assemblyvoting.model.EstadoSessao;

import java.time.LocalDateTime;

public final class EstadoUtils {
    public static EstadoSessao checkEstadoSessao(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio == null || fim == null) {
            return EstadoSessao.NAO_CONFIGURADA;
        }
        final LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(inicio) && now.isBefore(fim)) {
            return EstadoSessao.EMCURSO;
        } else {
            if (now.isAfter(fim)) {
                return EstadoSessao.TERMINADA;
            }
            return EstadoSessao.NAOINICIADA;
        }
    }

}
