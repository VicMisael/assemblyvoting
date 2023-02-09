package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AssociadoInexistenteException extends ResponseStatusException {
    public AssociadoInexistenteException() {
    super(HttpStatus.NOT_FOUND, "Associado inexistente");
    }
}
