package com.ksilisk.chatservice.payload.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageDto {
    @NotEmpty
    private String text;
    private long chatId;
}
