package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PautaOuAssociadoInexistentes extends ResponseStatusException {
    public PautaOuAssociadoInexistentes() {
        super(HttpStatus.BAD_REQUEST,"PautaOuAssociadoInexistentes");
    }
}
