package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.MessageDto;
import com.ksilisk.chatservice.payload.SendMessageDto;

import java.util.Set;

public interface MessageService {
    Set<MessageDto> getMessagesForChat(long chatId, String username);

    void send(SendMessageDto message, String ownerUsername);
}
