package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;

public class VotacaoNaoIniciadaException extends HttpException {
    public VotacaoNaoIniciadaException() {
        super("Votação não Iniciada", HttpStatus.BAD_REQUEST);
    }
}
