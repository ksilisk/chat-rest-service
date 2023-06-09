package com.ksilisk.chatservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
class ExceptionDetails {
    private final long timestamp;
    private final int statusCode;
    private final String message;
    private final String details;
}
