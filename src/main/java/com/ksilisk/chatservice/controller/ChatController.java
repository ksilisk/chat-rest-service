package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.request.CreateChatDto;
import com.ksilisk.chatservice.payload.response.ChatInfo;
import com.ksilisk.chatservice.service.ChatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Tag(name = "Chats")
@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping
    public Set<ChatInfo> getChats(JwtAuthenticationToken authenticationToken) {
        return chatService.getChats(authenticationToken);
    }

    @GetMapping("/{chatId}")
    public ChatInfo getChat(@PathVariable long chatId, JwtAuthenticationToken authenticationToken) {
        return chatService.getChat(chatId, authenticationToken);
    }

    @PostMapping
    public ChatInfo createChat(@RequestBody @Valid CreateChatDto chatDto, JwtAuthenticationToken authenticationToken) {
        return chatService.createChat(chatDto, authenticationToken);
    }

    @DeleteMapping("/{chatId}")
    public void deleteChat(@PathVariable long chatId, JwtAuthenticationToken authenticationToken) {
        chatService.deleteChat(chatId, authenticationToken);
    }
}
