package com.ksilisk.chatservice.exception;

public class ChatNotFoundException extends ApiException {
    private static final String DEFAULT_MESSAGE = "Chat not found";

    public ChatNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
