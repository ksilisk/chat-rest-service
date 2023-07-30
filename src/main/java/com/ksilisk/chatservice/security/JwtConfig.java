package com.ksilisk.chatservice.security;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "config.jwt")
public class JwtConfig {
    private static final long DEFAULT_EXPIRATION_TIME_SECONDS = 60 * 60;

    @NotEmpty
    private String secret;

    @Min(300)
    private long expirationTimeSeconds = DEFAULT_EXPIRATION_TIME_SECONDS;
}
