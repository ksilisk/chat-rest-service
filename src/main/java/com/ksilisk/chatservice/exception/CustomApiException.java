package com.ksilisk.chatservice.exception;

public class CustomApiException extends RuntimeException {
    public CustomApiException(String message) {
        super(message);
    }
}
