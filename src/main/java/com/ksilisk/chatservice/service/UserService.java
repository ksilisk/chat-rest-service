package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.response.UserInfo;

public interface UserService {
    UserInfo getUserById(long id);

    UserInfo getUserByUsername(String username);
}
