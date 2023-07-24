package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.UserDto;

public interface UserService {
    UserDto getUserById(long id);

    UserDto getUserByUsername(String username);
}
