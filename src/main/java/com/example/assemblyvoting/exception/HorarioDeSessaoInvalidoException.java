package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class HorarioDeSessaoInvalidoException extends ResponseStatusException {

    public HorarioDeSessaoInvalidoException() {
        super(HttpStatus.BAD_REQUEST, "Horario de Sessao Invalido");
    }
}
