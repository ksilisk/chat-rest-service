package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.RegisterDto;

public interface RegisterService {
    String register(RegisterDto registerDto);
}
