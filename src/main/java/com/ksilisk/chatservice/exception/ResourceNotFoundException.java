package com.ksilisk.chatservice.exception;

public class ResourceNotFoundException extends ApiException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
