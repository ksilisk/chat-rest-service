package com.ksilisk.chatservice.service.impl;

import com.ksilisk.chatservice.entity.Chat;
import com.ksilisk.chatservice.entity.User;
import com.ksilisk.chatservice.exception.ApiException;
import com.ksilisk.chatservice.exception.ResourceNotFoundException;
import com.ksilisk.chatservice.payload.ChatInfo;
import com.ksilisk.chatservice.payload.CreateChatDto;
import com.ksilisk.chatservice.repository.ChatRepository;
import com.ksilisk.chatservice.repository.UserRepository;
import com.ksilisk.chatservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final ModelMapper mm;

    public Set<ChatInfo> getChats(JwtAuthenticationToken jwtToken) {
        User user = userRepository.findUserByUsername(jwtToken.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User info not found"));
        return user.getChats().stream()
                .map(chat -> mm.map(chat, ChatInfo.class))
                .collect(toUnmodifiableSet());
    }

    @Override
    public void createChat(CreateChatDto chatDto, JwtAuthenticationToken jwtToken) {
        User owner = userRepository.findUserByUsername(jwtToken.getName())
                .orElseThrow(() -> new ApiException("Owner not found"));
        Set<User> users = chatDto.getUserIds().stream()
                .map(id -> userRepository.findUserById(id)
                        .orElseThrow(() -> new ApiException("User with id: " + id + "not found")))
                .collect(toUnmodifiableSet());
        chatRepository.save(Chat.builder()
                .name(chatDto.getName())
                .users(users)
                .owner(owner).build());
    }

    @Override
    public synchronized void deleteChat(long id, JwtAuthenticationToken jwtToken) {
        Chat chat = chatRepository.findChatById(id)
                .orElseThrow(() -> new ApiException("Chat not found"));
        User user = userRepository.findUserByUsername(jwtToken.getName())
                .orElseThrow(() -> new ApiException("User not found"));
        chat.getUsers().remove(user);
        setNewOwnerIfNeed(chat);
        chatRepository.save(chat);
    }

    @Override
    public ChatInfo getChat(long id, JwtAuthenticationToken authenticationToken) {
        // TODO implement this
        return null;
    }

    private void setNewOwnerIfNeed(Chat chat) {
        if (!chat.getUsers().contains(chat.getOwner()) && chat.getUsers().size() > 0) {
            User newOwner = chat.getUsers().stream()
                    .findAny()
                    .orElseThrow(() -> new ApiException("New owner not found"));
            chat.setOwner(newOwner);
        }
    }
}
