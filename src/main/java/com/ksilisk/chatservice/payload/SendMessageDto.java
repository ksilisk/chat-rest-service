package com.ksilisk.chatservice.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageDto {
    @NotEmpty(message = "Message text shouldn't be null or empty")
    private String text;
    private long chatId;
}
