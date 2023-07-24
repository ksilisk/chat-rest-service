package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.MessageDto;

import java.util.Set;

public interface MessageService {
    Set<MessageDto> getMessagesForChat(long chatId);
}
