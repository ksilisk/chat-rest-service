package com.ksilisk.chatservice.service.impl;

import com.ksilisk.chatservice.entity.Chat;
import com.ksilisk.chatservice.entity.Message;
import com.ksilisk.chatservice.entity.User;
import com.ksilisk.chatservice.exception.ApiException;
import com.ksilisk.chatservice.payload.MessageDto;
import com.ksilisk.chatservice.payload.SendMessageDto;
import com.ksilisk.chatservice.repository.ChatRepository;
import com.ksilisk.chatservice.repository.MessageRepository;
import com.ksilisk.chatservice.repository.UserRepository;
import com.ksilisk.chatservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public Set<MessageDto> getMessagesForChat(long chatId, String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ApiException("User not found"));
        Chat chat = chatRepository.findChatById(chatId)
                .orElseThrow(() -> new ApiException("Chat not found"));
        if (!chat.getUsers().contains(user)) {
            throw new ApiException("Chat not found");
        }
        return chat.getMessages().stream()
                .map(MessageDto::from)
                .collect(toUnmodifiableSet());
    }

    @Override
    public void send(SendMessageDto message, String ownerUsername) {
        User user = userRepository.findUserByUsername(ownerUsername)
                .orElseThrow(() -> new ApiException("User not found"));
        Chat chat = chatRepository.findChatById(message.getChatId())
                .orElseThrow(() -> new ApiException("Chat not found"));
        if (!user.getChats().contains(chat)) {
            throw new ApiException("Chat not found");
        }
        messageRepository.save(Message.builder()
                .chat(chat)
                .user(user)
                .time(Timestamp.from(Instant.now()))
                .text(message.getText())
                .build());
    }
}
