package com.ksilisk.chatservice.controller;

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
    public String register(@RequestBody RegisterDto register) {
        return registerService.register(register);
    }
}
