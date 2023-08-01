package com.ksilisk.chatservice.service.impl;

import com.ksilisk.chatservice.entity.User;
import com.ksilisk.chatservice.exception.ApiException;
import com.ksilisk.chatservice.payload.request.AuthInfo;
import com.ksilisk.chatservice.repository.UserRepository;
import com.ksilisk.chatservice.security.TokenProvider;
import com.ksilisk.chatservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String auth(AuthInfo authInfo) {
        User user = userRepository.findByUsernameOrEmail(authInfo.getUsernameOrEmail())
                .orElseThrow(() -> new ApiException("Bad credential"));
        if (!passwordEncoder.matches(authInfo.getPassword(), user.getPassword())) {
            throw new ApiException("Bad credential");
        }
        return tokenProvider.create(user.getUsername(), user.getId());
    }
}