package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;

public class VotoDuplicadoException extends HttpException {
    public VotoDuplicadoException() {
        super("Voto Duplicado", HttpStatus.BAD_REQUEST);
    }
}
