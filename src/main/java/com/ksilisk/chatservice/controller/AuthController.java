package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.JwtAuthResponse;
import com.ksilisk.chatservice.payload.AuthInfo;
import com.ksilisk.chatservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public JwtAuthResponse auth(@RequestBody @Valid AuthInfo authInfo) {
        String token = authService.auth(authInfo);
        return new JwtAuthResponse(token);
    }
}
