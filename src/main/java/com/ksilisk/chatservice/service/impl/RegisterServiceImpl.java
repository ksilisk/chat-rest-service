package com.ksilisk.chatservice.service.impl;

import com.ksilisk.chatservice.entity.User;
import com.ksilisk.chatservice.exception.ApiException;
import com.ksilisk.chatservice.payload.request.RegisterInfo;
import com.ksilisk.chatservice.repository.UserRepository;
import com.ksilisk.chatservice.security.TokenProvider;
import com.ksilisk.chatservice.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Override
    public String register(RegisterInfo registerInfo) {
        if (userRepository.existsByUsernameOrEmail(registerInfo.getUsername(), registerInfo.getEmail())) {
            throw new ApiException("User already exists");
        }
        String encodedPassword = passwordEncoder.encode(registerInfo.getPassword());
        registerInfo.setPassword(encodedPassword);
        User user = userRepository.save(User.builder()
                .username(registerInfo.getUsername())
                .password(registerInfo.getPassword())
                .email(registerInfo.getEmail()).build());
        return tokenProvider.create(registerInfo.getUsername(), user.getId());
    }
}
