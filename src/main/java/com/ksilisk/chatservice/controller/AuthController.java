package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.LoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok("Login Successfully");
    }
}
