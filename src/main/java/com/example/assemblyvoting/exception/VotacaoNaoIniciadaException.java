package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VotacaoNaoIniciadaException extends ResponseStatusException {
    public VotacaoNaoIniciadaException() {
        super(HttpStatus.BAD_REQUEST, "Votação não Iniciada");
    }
}
