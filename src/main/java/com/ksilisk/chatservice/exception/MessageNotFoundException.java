package com.ksilisk.chatservice.exception;

public class MessageNotFoundException extends ApiException {
    private static final String DEFAULT_MESSAGE = "Message not found";
    public MessageNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
