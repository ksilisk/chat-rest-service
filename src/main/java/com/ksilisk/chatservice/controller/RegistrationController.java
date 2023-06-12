package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.RegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RegistrationController {

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto register) {
        return ResponseEntity.ok("Registration Successfully");
    }
}
