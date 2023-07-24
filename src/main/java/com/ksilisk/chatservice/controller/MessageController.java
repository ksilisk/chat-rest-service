package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.MessageDto;
import com.ksilisk.chatservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/{chatId}")
    public Set<MessageDto> getMessagesForChat(@PathVariable long chatId) {
        return messageService.getMessagesForChat(chatId);
    }
}
