package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.ChatInfo;
import com.ksilisk.chatservice.payload.CreateChatDto;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Set;

public interface ChatService {
    Set<ChatInfo> getChats(JwtAuthenticationToken jwtToken);

    void createChat(CreateChatDto chatDto, JwtAuthenticationToken jwtToken);

    void deleteChat(long id, JwtAuthenticationToken jwtToken);

    ChatInfo getChat(long id, JwtAuthenticationToken authenticationToken);
}
