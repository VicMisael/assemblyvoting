package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PautaInexistenteException extends ResponseStatusException {

    public PautaInexistenteException() {
     super(HttpStatus.NOT_FOUND, "Pauta inexistente");
    }
}
