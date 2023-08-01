package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.response.MessageInfo;
import com.ksilisk.chatservice.payload.request.SendMessageDto;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface MessageService {
    MessageInfo getMessage(long messageId, JwtAuthenticationToken authenticationToken);

    MessageInfo send(SendMessageDto message, JwtAuthenticationToken authenticationToken);

    void deleteMessage(long messageId, JwtAuthenticationToken authenticationToken);
}
