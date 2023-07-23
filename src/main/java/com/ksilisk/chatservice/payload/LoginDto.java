package com.ksilisk.chatservice.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    @NotEmpty
    private String usernameOrEmail;

    @NotEmpty
    private String password;
}
