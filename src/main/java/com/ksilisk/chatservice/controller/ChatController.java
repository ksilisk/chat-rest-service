package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.ChatDto;
import com.ksilisk.chatservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/my")
    public Set<ChatDto> getChats(Principal principal) {
        return chatService.getChatsForUser(principal.getName());
    }

    @PostMapping("/create")
    public void createChat() {
        // TODO implement create chat endpoint
    }
}
