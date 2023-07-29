package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.MessageDto;
import com.ksilisk.chatservice.payload.SendMessageDto;
import com.ksilisk.chatservice.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/{chatId}")
    public Set<MessageDto> getMessagesForChat(@PathVariable long chatId, Principal principal) {
        return messageService.getMessagesForChat(chatId, principal.getName());
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody @Valid SendMessageDto messageDto, Principal principal) {
        messageService.send(messageDto, principal.getName());
    }
}
