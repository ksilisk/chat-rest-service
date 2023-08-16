package com.ksilisk.chatservice.payload.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthInfo {
    @NotEmpty
    private String usernameOrEmail;

    @NotEmpty
    private String password;
}
