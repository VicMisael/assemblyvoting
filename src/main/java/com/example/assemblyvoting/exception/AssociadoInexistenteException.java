package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;

public class AssociadoInexistenteException extends HttpException {
    public AssociadoInexistenteException() {
        super("Associado inexistente", HttpStatus.BAD_REQUEST);
    }
}
