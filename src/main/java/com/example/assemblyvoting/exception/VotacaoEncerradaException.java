package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VotacaoEncerradaException extends ResponseStatusException {
    public VotacaoEncerradaException() {
        super(HttpStatus.BAD_REQUEST, "Votação encerrada");
    }
}
