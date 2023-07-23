package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.UserDto;
import com.ksilisk.chatservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/me")
    public UserDto getMyInfo(Principal principal) {
        return userService.getUserByUsername(principal.getName());
    }
}
