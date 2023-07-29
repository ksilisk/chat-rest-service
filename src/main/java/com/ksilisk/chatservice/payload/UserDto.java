package com.ksilisk.chatservice.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private long id;
    private String username;
    private String email;
}
