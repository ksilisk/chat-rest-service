package com.ksilisk.chatservice.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {
    @Min(value = 1)
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Chat name should have at least 2 characters")
    private String name;

    @NotNull(message = "Chat owner shouldn't be null")
    private UserDto owner;
}
