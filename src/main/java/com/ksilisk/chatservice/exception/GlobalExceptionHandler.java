package com.ksilisk.chatservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ExceptionDetails handleApiException(Exception ex, WebRequest webRequest) {
        log.warn("Handled api exception. Description: {}", webRequest.getDescription(false), ex);
        return new ExceptionDetails(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(), webRequest.getDescription(false));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ExceptionDetails handleAnyException(Exception ex, WebRequest webRequest) {
        log.warn("Handled any exception. Description: {}", webRequest.getDescription(false), ex);
        return new ExceptionDetails(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), webRequest.getDescription(false));
    }
}
