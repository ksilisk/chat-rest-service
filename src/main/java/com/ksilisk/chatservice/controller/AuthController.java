package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.LoginDto;
import com.ksilisk.chatservice.service.AuthService;
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
    public String auth(@RequestBody LoginDto loginDto) {
        authService.login(loginDto);
        return "Login Successfully";
    }
}
