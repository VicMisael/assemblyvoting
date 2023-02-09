package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;

public class PautaOuAssociadoInexistentes extends ResponseStatusException {
    public PautaOuAssociadoInexistentes() {
        super(HttpStatus.BAD_REQUEST,"PautaOuAssociadoInexistentes");
    }
}
