package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.MessageInfo;
import com.ksilisk.chatservice.payload.SendMessageDto;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface MessageService {
    MessageInfo getMessage(long messageId, JwtAuthenticationToken authenticationToken);

    void send(SendMessageDto message, JwtAuthenticationToken authenticationToken);

    void deleteMessage(long messageId, JwtAuthenticationToken authenticationToken);
}
