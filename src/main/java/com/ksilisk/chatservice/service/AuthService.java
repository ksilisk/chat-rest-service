package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.AuthInfo;

public interface AuthService {
    String auth(AuthInfo authInfo);
}
