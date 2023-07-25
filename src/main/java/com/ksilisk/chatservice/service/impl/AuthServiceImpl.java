package com.ksilisk.chatservice.service.impl;

import com.ksilisk.chatservice.payload.AuthInfo;
import com.ksilisk.chatservice.security.TokenProvider;
import com.ksilisk.chatservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Override
    public String auth(AuthInfo authInfo) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authInfo.getUsernameOrEmail(), authInfo.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return tokenProvider.create(authentication.getName());
    }
}