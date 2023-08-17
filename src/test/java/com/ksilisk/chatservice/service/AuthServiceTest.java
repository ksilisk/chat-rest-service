package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.entity.User;
import com.ksilisk.chatservice.exception.ApiException;
import com.ksilisk.chatservice.payload.request.AuthInfo;
import com.ksilisk.chatservice.repository.UserRepository;
import com.ksilisk.chatservice.security.TokenProvider;
import com.ksilisk.chatservice.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@DataJpaTest
class AuthServiceTest {
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void validAuthInfoTest_shouldAuthUser() {
        // given
        TokenProvider tokenProvider = Mockito.mock(TokenProvider.class);
        User user = User.builder()
                .email("Ksilisk9@yandex.ru")
                .password(passwordEncoder.encode("ksilisk"))
                .username("ksilisk")
                .build();
        AuthInfo authInfo = new AuthInfo("ksilisk", "ksilisk");
        long id = userRepository.save(user).getId();
        AuthService authService = new AuthServiceImpl(userRepository, tokenProvider, passwordEncoder);
        // when
        authService.auth(authInfo);
        // then
        Mockito.verify(tokenProvider).create("ksilisk", id);
    }

    @Test
    void invalidAuthInfoTest_ShouldThrowApiException() {
        // given
        TokenProvider tokenProvider = Mockito.mock(TokenProvider.class);
        AuthInfo authInfo = new AuthInfo("ksilisk1", "ksilisk1");
        AuthService authService = new AuthServiceImpl(userRepository, tokenProvider, passwordEncoder);
        // then
        Assertions.assertThrows(ApiException.class, () -> authService.auth(authInfo));
    }
}