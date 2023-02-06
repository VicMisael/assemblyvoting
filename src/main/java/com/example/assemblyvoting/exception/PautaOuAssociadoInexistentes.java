package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;

public class PautaOuAssociadoInexistentes extends HttpException {
    public PautaOuAssociadoInexistentes() {
        super("PautaOuAssociadoInexistentes", HttpStatus.BAD_REQUEST);
    }
}
