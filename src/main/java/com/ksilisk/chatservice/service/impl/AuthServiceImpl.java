package com.ksilisk.chatservice.service.impl;

import com.ksilisk.chatservice.payload.LoginDto;
import com.ksilisk.chatservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Override
    public String login(LoginDto loginDto) {
        return null;
    }
}
