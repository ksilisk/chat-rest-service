package com.ksilisk.chatservice.service.impl;

import com.ksilisk.chatservice.entity.Chat;
import com.ksilisk.chatservice.exception.ApiException;
import com.ksilisk.chatservice.payload.MessageDto;
import com.ksilisk.chatservice.repository.ChatRepository;
import com.ksilisk.chatservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final ChatRepository chatRepository;
    private final ModelMapper mm;

    @Override
    public Set<MessageDto> getMessagesForChat(long chatId) {
        Chat chat = chatRepository.findChatById(chatId)
                .orElseThrow(() -> new ApiException("Chat not found"));
        return chat.getMessages().stream()
                .map(message -> mm.map(message, MessageDto.class))
                .collect(toUnmodifiableSet());
    }
}
