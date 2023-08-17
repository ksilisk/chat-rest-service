package com.ksilisk.chatservice.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
