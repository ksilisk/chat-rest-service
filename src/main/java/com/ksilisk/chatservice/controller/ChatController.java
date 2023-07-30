package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.ChatDto;
import com.ksilisk.chatservice.payload.CreateChatDto;
import com.ksilisk.chatservice.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping
    public Set<ChatDto> getChats(JwtAuthenticationToken authenticationToken) {
        return chatService.getChats(authenticationToken);
    }

    @PostMapping("/create")
    public void createChat(@RequestBody @Valid CreateChatDto chatDto, JwtAuthenticationToken authenticationToken) {
        chatService.createChat(chatDto, authenticationToken);
    }

    @GetMapping("/delete/{chatId}")
    public void deleteChat(@PathVariable long chatId, JwtAuthenticationToken authenticationToken) {
        chatService.deleteChat(chatId, authenticationToken);
    }
}
