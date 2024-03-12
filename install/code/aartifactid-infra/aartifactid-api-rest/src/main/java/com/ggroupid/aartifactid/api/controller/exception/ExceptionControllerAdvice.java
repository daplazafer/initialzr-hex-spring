package com.ggroupid.aartifactid.api.controller.exception;

import com.ggroupid.aartifactid.api.dto.ExceptionDto;
import com.ggroupid.aartifactid.domain.exception.HelloWorldNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionDto defaultException(Exception exception) {
        return new ExceptionDto(exception.getMessage(), Instant.now());
    }

    @ExceptionHandler(HelloWorldNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionDto helloWorldNotFound(Exception exception) {
        return new ExceptionDto(exception.getMessage(), Instant.now());
    }

}
