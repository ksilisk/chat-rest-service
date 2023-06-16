package com.ksilisk.chatservice.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExceptionDetails {
    private final long timestamp;
    private final int statusCode;
    private final String message;
    private final String details;
}
