package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.response.JwtAuthResponse;
import com.ksilisk.chatservice.payload.request.RegisterInfo;
import com.ksilisk.chatservice.service.RegisterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Registration")
@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegisterService registerService;

    @PostMapping
    public JwtAuthResponse register(@RequestBody @Valid RegisterInfo register) {
        String token = registerService.register(register);
        return new JwtAuthResponse(token);
    }
}
