package com.ksilisk.chatservice.service.impl;

import com.ksilisk.chatservice.entity.Chat;
import com.ksilisk.chatservice.entity.Message;
import com.ksilisk.chatservice.entity.User;
import com.ksilisk.chatservice.exception.ApiException;
import com.ksilisk.chatservice.exception.MessageNotFoundException;
import com.ksilisk.chatservice.exception.UserNotFoundException;
import com.ksilisk.chatservice.payload.request.SendMessageDto;
import com.ksilisk.chatservice.payload.response.MessageInfo;
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
                .orElseThrow(UserNotFoundException::new);
        Message message = messageRepository.findMessageById(messageId)
                .orElseThrow(MessageNotFoundException::new);
        if (!user.getChats().contains(message.getChat())) {
            throw new MessageNotFoundException();
        }
        return MessageInfo.from(message);
    }

    @Override
    public MessageInfo send(SendMessageDto message, JwtAuthenticationToken authenticationToken) {
        User user = userRepository.findUserByUsername(authenticationToken.getName())
                .orElseThrow(() -> new ApiException("User not found"));
        Chat chat = chatRepository.findChatById(message.getChatId())
                .orElseThrow(() -> new ApiException("Chat not found"));
        if (!user.getChats().contains(chat)) {
            throw new ApiException("Chat not found");
        }
        Message newMessage = messageRepository.save(Message.builder()
                .chat(chat)
                .user(user)
                .time(Timestamp.from(Instant.now()))
                .text(message.getText())
                .build());
        return MessageInfo.from(newMessage);
    }

    @Override
    public void deleteMessage(long messageId, JwtAuthenticationToken authenticationToken) {
        Message message = messageRepository.findMessageById(messageId)
                        .orElseThrow(MessageNotFoundException::new);
        User owner = userRepository.findUserByUsername(authenticationToken.getName())
                .orElseThrow(UserNotFoundException::new);
        if (!message.getUser().equals(owner)) {
            throw new ApiException("You can't delete this message");
        }
        messageRepository.deleteById(messageId);
    }
}
