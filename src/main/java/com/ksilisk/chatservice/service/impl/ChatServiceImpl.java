package com.ksilisk.chatservice.service.impl;

import com.ksilisk.chatservice.entity.User;
import com.ksilisk.chatservice.exception.ResourceNotFoundException;
import com.ksilisk.chatservice.payload.ChatDto;
import com.ksilisk.chatservice.repository.UserRepository;
import com.ksilisk.chatservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final UserRepository userRepository;
    private final ModelMapper mm;

    public Set<ChatDto> getChatsForUser(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User info not found"));
        return user.getChats().stream()
                .map(chat -> mm.map(chat, ChatDto.class))
                .collect(toUnmodifiableSet());
    }
}
