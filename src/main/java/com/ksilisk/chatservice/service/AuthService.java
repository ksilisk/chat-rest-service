package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.LoginDto;

public interface AuthService {
    void login(LoginDto loginDto);
}
