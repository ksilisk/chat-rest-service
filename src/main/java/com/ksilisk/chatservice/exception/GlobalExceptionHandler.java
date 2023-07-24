package com.ksilisk.chatservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ExceptionDetails handleResourceNotFound(Exception ex, WebRequest webRequest) {
        log.info("Handled resource not found exception. Description {}",
                webRequest.getDescription(false), ex);
        return ExceptionDetails.builder()
                .details(webRequest.getDescription(false))
                .message(ex.getMessage())
                .timestamp(System.currentTimeMillis())
                .statusCode(NOT_FOUND.value())
                .build();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ExceptionDetails handleApiException(Exception ex, WebRequest webRequest) {
        log.info("Handled api exception. Description: {}", webRequest.getDescription(false), ex);
        return ExceptionDetails.builder()
                .timestamp(System.currentTimeMillis())
                .message(ex.getMessage())
                .details(webRequest.getDescription(false))
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .build();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ExceptionDetails handleAnyException(Exception ex, WebRequest webRequest) {
        log.warn("Handled any exception. Description: {}", webRequest.getDescription(false), ex);
        return ExceptionDetails.builder()
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .details(webRequest.getDescription(false))
                .message(ex.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
