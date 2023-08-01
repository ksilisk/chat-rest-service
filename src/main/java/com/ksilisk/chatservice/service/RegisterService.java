package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.request.RegisterInfo;

public interface RegisterService {
    String register(RegisterInfo registerInfo);
}
