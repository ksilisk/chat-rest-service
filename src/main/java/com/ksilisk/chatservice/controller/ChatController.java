package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.ChatDto;
import com.ksilisk.chatservice.payload.CreateChatDto;
import com.ksilisk.chatservice.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping
    public Set<ChatDto> getChats(Principal principal) {
        return chatService.getChats(principal.getName());
    }

    @PostMapping("/create")
    public void createChat(@RequestBody @Valid CreateChatDto chatDto, Principal principal) {
        chatService.createChat(chatDto, principal.getName());
    }

    @GetMapping("/delete/{chatId}")
    public void deleteChat(@PathVariable long chatId, Principal principal) {
        chatService.deleteChat(chatId, principal.getName());
    }
}
