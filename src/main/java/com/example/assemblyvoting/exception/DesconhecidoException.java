package com.example.assemblyvoting.exception;

import org.springframework.http.HttpStatus;

public class DesconhecidoException extends HttpException {

    public DesconhecidoException() {
        super("Erro desconhecido", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
