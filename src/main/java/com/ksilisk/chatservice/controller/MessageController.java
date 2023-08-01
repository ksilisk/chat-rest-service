package com.ksilisk.chatservice.controller;

import com.ksilisk.chatservice.payload.response.MessageInfo;
import com.ksilisk.chatservice.payload.request.SendMessageDto;
import com.ksilisk.chatservice.service.MessageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Messages")
@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/{messageId}")
    public MessageInfo getMessage(@PathVariable long messageId, JwtAuthenticationToken authenticationToken) {
        return messageService.getMessage(messageId, authenticationToken);
    }

    @DeleteMapping("/{messageId}")
    public void deleteMessage(@PathVariable long messageId, JwtAuthenticationToken authenticationToken) {
        messageService.deleteMessage(messageId, authenticationToken);
    }

    @PostMapping
    public MessageInfo sendMessage(@RequestBody @Valid SendMessageDto messageDto, JwtAuthenticationToken authenticationToken) {
        return messageService.send(messageDto, authenticationToken);
    }
}
