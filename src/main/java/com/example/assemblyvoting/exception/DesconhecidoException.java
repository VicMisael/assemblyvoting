package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DesconhecidoException extends ResponseStatusException {

    public DesconhecidoException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Erro desconhecido");
    }
}
