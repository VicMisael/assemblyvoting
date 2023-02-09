package com.example.assemblyvoting.config.errorhandler;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
@Order(-2)
@Slf4j
public class WebExceptionHandler extends AbstractErrorWebExceptionHandler {

    public WebExceptionHandler(ErrorAttributes errorAttributes, ApplicationContext applicationContext, ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, new WebProperties.Resources(), applicationContext);
        super.setMessageReaders(serverCodecConfigurer.getReaders());
        super.setMessageWriters(serverCodecConfigurer.getWriters());

    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        return generateErrorResponse(getError(request));
    }

    private Mono<ServerResponse> generateErrorResponse(Throwable error) {
        if (error instanceof ResponseStatusException) {
            final ResponseStatusException castError = (ResponseStatusException) error;
            return ServerResponse.status(castError.getStatus())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(ErrorResponse.builder()
                            .httpStatus(castError.getStatus())
                            .message(castError.getReason())
                            .build());
        } else if (error instanceof RuntimeException) {
            log.error("Runtime Exception: " + error.getMessage());
            error.printStackTrace();
            return ServerResponse.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(ErrorResponse.builder().message("Verifique a requisição ou Tente novamente mais tarde")
                            .httpStatus(HttpStatus.BAD_REQUEST).build());
        }
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                .bodyValue(ErrorResponse.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).message("Erro de servidor").build());
    }

    @RequiredArgsConstructor
    @Getter
    @Builder
    private static class ErrorResponse {
        private final HttpStatus httpStatus;
        private final String message;
    }

}
