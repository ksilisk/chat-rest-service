package com.ksilisk.chatservice.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterInfo {
    @NotEmpty
    @Size(min = 5, message = "Username should have at least 5 characters")
    private String username;

    @NotEmpty
    @Size(min = 5, message = "Password should have at least 5 characters")
    private String password;

    @Email
    private String email;
}
