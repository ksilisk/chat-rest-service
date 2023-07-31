package com.ksilisk.chatservice.service.impl;

import com.ksilisk.chatservice.entity.Chat;
import com.ksilisk.chatservice.entity.Message;
import com.ksilisk.chatservice.entity.User;
import com.ksilisk.chatservice.exception.ApiException;
import com.ksilisk.chatservice.payload.MessageInfo;
import com.ksilisk.chatservice.payload.SendMessageDto;
import com.ksilisk.chatservice.repository.ChatRepository;
import com.ksilisk.chatservice.repository.MessageRepository;
import com.ksilisk.chatservice.repository.UserRepository;
import com.ksilisk.chatservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Override
    public MessageInfo getMessage(long messageId, JwtAuthenticationToken authenticationToken) {
        User user = userRepository.findUserByUsername(authenticationToken.getName())
                .orElseThrow(() -> new ApiException("User not found"));
        Chat chat = chatRepository.findChatById(messageId)
                .orElseThrow(() -> new ApiException("Chat not found"));
        if (!chat.getUsers().contains(user)) {
            throw new ApiException("Chat not found");
        }
        return MessageInfo.from(messageRepository.findMessageById(messageId)
                .orElseThrow(() -> new ApiException("Message not found")));
    }

    @Override
    public void send(SendMessageDto message, JwtAuthenticationToken authenticationToken) {
        User user = userRepository.findUserByUsername(authenticationToken.getName())
                .orElseThrow(() -> new ApiException("User not found"));
        Chat chat = chatRepository.findChatById(message.getChatId())
                .orElseThrow(() -> new ApiException("Chat not found"));
        if (!user.getChats().contains(chat)) {
            throw new ApiException("Chat not found");
        }
        Message dbMessage = Message.builder()
                .chat(chat)
                .user(user)
                .time(Timestamp.from(Instant.now()))
                .text(message.getText())
                .build();
        messageRepository.save(dbMessage);
    }

    @Override
    public void deleteMessage(long messageId, JwtAuthenticationToken authenticationToken) {
        // TODO implement this;
    }
}
