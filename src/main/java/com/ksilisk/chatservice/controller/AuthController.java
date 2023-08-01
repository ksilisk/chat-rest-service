package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.response.JwtAuthResponse;
import com.ksilisk.chatservice.payload.request.AuthInfo;
import com.ksilisk.chatservice.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public JwtAuthResponse auth(@RequestBody @Valid AuthInfo authInfo) {
        String token = authService.auth(authInfo);
        return new JwtAuthResponse(token);
    }
}
