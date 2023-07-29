package com.ksilisk.chatservice.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDto {
    private long id;
    private String name;
    private long ownerId;
}
