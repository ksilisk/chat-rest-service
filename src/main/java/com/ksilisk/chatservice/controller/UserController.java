package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.response.UserInfo;
import com.ksilisk.chatservice.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Tag(name = "Users")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserInfo getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/me")
    public UserInfo getMyInfo(Principal principal) {
        return userService.getUserByUsername(principal.getName());
    }
}
