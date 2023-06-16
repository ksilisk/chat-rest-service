package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
