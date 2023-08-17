package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.entity.User;
import com.ksilisk.chatservice.exception.ApiException;
import com.ksilisk.chatservice.payload.request.RegisterInfo;
import com.ksilisk.chatservice.repository.UserRepository;
import com.ksilisk.chatservice.security.TokenProvider;
import com.ksilisk.chatservice.service.impl.RegisterServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@DataJpaTest
class RegisterServiceTest {
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void newUserRegisterTest_shouldCreateUser() {
        // given
        TokenProvider tokenProvider = Mockito.mock(TokenProvider.class);
        RegisterInfo registerInfo = new RegisterInfo("ksilisk", "ksilisk", "ksilisk@mail.ru");
        RegisterService registerService = new RegisterServiceImpl(userRepository, passwordEncoder, tokenProvider);
        // when
        registerService.register(registerInfo);
        // then
        Optional<User> user = userRepository.findUserByUsername("ksilisk");
        Assertions.assertTrue(user.isPresent());
        Mockito.verify(tokenProvider).create("ksilisk", user.get().getId());
    }

    @Test
    void userForRegisterExistsTest_shouldThrowApiException() {
        // given
        TokenProvider tokenProvider = Mockito.mock(TokenProvider.class);
        RegisterInfo registerInfo = new RegisterInfo("ksilisk", "ksilisk", "ksilisk@mail.ru");
        RegisterService registerService = new RegisterServiceImpl(userRepository, passwordEncoder, tokenProvider);
        User user = User.builder()
                .email("ksilisk@mail.ru")
                .username("ksilisk")
                .password("ksilisk")
                .build();
        userRepository.save(user);
        // then
        Assertions.assertThrows(ApiException.class, () -> registerService.register(registerInfo));
    }
}