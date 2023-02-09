package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VotoDuplicadoException extends ResponseStatusException {
    public VotoDuplicadoException() {
        super(HttpStatus.BAD_REQUEST, "Voto Duplicado");
    }
}
