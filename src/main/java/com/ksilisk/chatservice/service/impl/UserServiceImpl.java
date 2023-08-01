package com.ksilisk.chatservice.service.impl;

import com.ksilisk.chatservice.entity.User;
import com.ksilisk.chatservice.exception.UserNotFoundException;
import com.ksilisk.chatservice.payload.response.UserInfo;
import com.ksilisk.chatservice.repository.UserRepository;
import com.ksilisk.chatservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserInfo getUserById(long id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(UserNotFoundException::new);
        return UserInfo.from(user);
    }

    @Override
    public UserInfo getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        return UserInfo.from(user);
    }
}
