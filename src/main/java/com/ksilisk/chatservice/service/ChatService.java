package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.ChatDto;
import com.ksilisk.chatservice.payload.CreateChatDto;

import java.util.Set;

public interface ChatService {
    Set<ChatDto> getChats(String username);

    void createChat(CreateChatDto chatDto, String ownerUsername);

    void deleteChat(long id, String username);
}
