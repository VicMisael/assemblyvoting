package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SemDadosSessaoException extends ResponseStatusException {
    public SemDadosSessaoException() {
        super(HttpStatus.BAD_REQUEST, "Sessão sem dados");
    }
}
