package com.ksilisk.chatservice.service.impl;

import com.ksilisk.chatservice.payload.RegisterDto;
import com.ksilisk.chatservice.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    @Override
    public String register(RegisterDto registerDto) {
        return null;
    }
}
