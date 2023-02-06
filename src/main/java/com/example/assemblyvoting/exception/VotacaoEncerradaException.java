package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;

public class VotacaoEncerradaException extends HttpException {
    public VotacaoEncerradaException() {
        super("Votação encerrada", HttpStatus.BAD_REQUEST);
    }
}
