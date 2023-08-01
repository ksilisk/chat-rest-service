package com.ksilisk.chatservice.service;

import com.ksilisk.chatservice.payload.response.ChatInfo;
import com.ksilisk.chatservice.payload.request.CreateChatDto;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Set;

public interface ChatService {
    Set<ChatInfo> getChats(JwtAuthenticationToken jwtToken);

    ChatInfo createChat(CreateChatDto chatDto, JwtAuthenticationToken jwtToken);

    void deleteChat(long id, JwtAuthenticationToken jwtToken);

    ChatInfo getChat(long id, JwtAuthenticationToken authenticationToken);
}
