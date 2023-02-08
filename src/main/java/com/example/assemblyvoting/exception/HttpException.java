package com.example.assemblyvoting.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Slf4j
public abstract class HttpException extends RuntimeException {
    private final HttpStatus httpStatus;

    HttpException(String message, HttpStatus httpStatus) {
        super(message);
        log.error(this.toString());
        this.httpStatus = httpStatus;
    }
}
