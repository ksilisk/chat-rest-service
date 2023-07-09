package com.ksilisk.chatservice.payload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtAuthResponse {
    private final String accessToken;
    private final String tokenType = "bearer";
}
