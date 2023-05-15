package com.ksilisk.chatservice.security;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("config.jwt")
public class JwtProperties {
    @NotBlank
    private String secret;
    private long expirationTime;
}
