package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.ChatDto;

import java.util.Set;

public interface ChatService {
    Set<ChatDto> getChatsForUser(String username);
}
