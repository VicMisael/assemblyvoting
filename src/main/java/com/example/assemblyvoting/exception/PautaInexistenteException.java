package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;

public class PautaInexistenteException extends HttpException {

    public PautaInexistenteException() {
        super("Pauta inexistente", HttpStatus.BAD_REQUEST);
    }
}
