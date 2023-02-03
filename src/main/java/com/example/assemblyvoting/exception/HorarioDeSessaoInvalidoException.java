package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;

public class HorarioDeSessaoInvalidoException extends HttpException {

    public HorarioDeSessaoInvalidoException() {
        super("Horario de Sessao Invalido", HttpStatus.BAD_REQUEST);
    }
}
