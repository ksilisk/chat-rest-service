package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.ChatDto;
import com.ksilisk.chatservice.payload.CreateChatDto;

import java.util.Set;

public interface ChatService {
    Set<ChatDto> getChatsForUser(String username);

    void createChat(CreateChatDto chatDto, String ownerUsername);
}
