package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.request.AuthInfo;

public interface AuthService {
    String auth(AuthInfo authInfo);
}
