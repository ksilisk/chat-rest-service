package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.JwtAuthResponse;
import com.ksilisk.chatservice.payload.RegisterDto;
import com.ksilisk.chatservice.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegisterService registerService;

    @PostMapping("/register")
    public JwtAuthResponse register(@RequestBody RegisterDto register) {
        String token = registerService.register(register);
        return new JwtAuthResponse(token);
    }
}
