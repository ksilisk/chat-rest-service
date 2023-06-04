package com.ksilisk.chatservice.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChatDto {
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Chat name should have at least 2 characters")
    private String name;

    @NotNull(message = "Chat owner shouldn't be null")
    private UserDto owner;
}
