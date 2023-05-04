package com.ksilisk.chatservice.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageDto {
    private Long id;

    @NotEmpty(message = "Message text shouldn't be null or empty")
    private String text;

    @NotNull(message = "User who sent the message shouldn't be null")
    private UserDto user;

    @NotNull(message = "Chat to which the message was sent cannot be null")
    private ChatDto chat;
}
