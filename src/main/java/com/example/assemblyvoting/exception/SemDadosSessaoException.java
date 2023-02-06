package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;

public class SemDadosSessaoException extends HttpException {
    public SemDadosSessaoException() {
        super("Sessão sem dados", HttpStatus.BAD_REQUEST);
    }
}
