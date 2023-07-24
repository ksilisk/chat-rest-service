package com.ksilisk.chatservice.service.impl;

import com.ksilisk.chatservice.entity.User;
import com.ksilisk.chatservice.exception.ResourceNotFoundException;
import com.ksilisk.chatservice.payload.UserDto;
import com.ksilisk.chatservice.repository.UserRepository;
import com.ksilisk.chatservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mm;

    @Override
    public UserDto getUserById(long id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return mm.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return mm.map(user, UserDto.class);
    }
}
