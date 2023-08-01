package com.ksilisk.chatservice.exception;

public class UserNotFoundException extends ApiException {
    private static final String DEFAULT_MESSAGE = "User not found";

    public UserNotFoundException() {
        this(DEFAULT_MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
