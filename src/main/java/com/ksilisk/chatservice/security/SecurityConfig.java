package com.ksilisk.chatservice.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

import java.time.Duration;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public JwtTimestampValidator jwtTimestampValidator(JwtConfig jwtConfig) {
        return new JwtTimestampValidator(Duration.ofMillis(jwtConfig.getExpirationTimeSeconds()));
    }

    @Bean
    public JwtEncoder jwtEncoder(JwtConfig jwtConfig) {
        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtConfig.getSecretKey()));
    }

    @Bean
    public JwtDecoder jwtDecoder(JwtConfig jwtConfig, JwtTimestampValidator jwtTimestampValidator) {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(jwtConfig.getSecretKey())
                .macAlgorithm(MacAlgorithm.HS256).build();
        jwtDecoder.setJwtValidator(jwtTimestampValidator);
        return jwtDecoder;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtDecoder jwtDecoder) throws Exception {
        http.csrf().disable().authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/api/v1/auth").permitAll()
                                .requestMatchers("/api/v1/register").permitAll()
                                .anyRequest().authenticated())
                .exceptionHandling(handling ->
                        handling.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                                .accessDeniedHandler(new BearerTokenAccessDeniedHandler()))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder)));
        return http.build();
    }
}
