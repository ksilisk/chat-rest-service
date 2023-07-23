package com.ksilisk.chatservice.exception;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
class ExceptionDetails {
    private final long timestamp;
    private final int statusCode;
    private final String message;
    private final String details;
}
