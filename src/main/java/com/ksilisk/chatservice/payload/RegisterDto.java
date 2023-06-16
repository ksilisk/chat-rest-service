package com.ksilisk.chatservice.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotEmpty
    @Size(min = 5, message = "Username should have at least 5 characters")
    private String username;

    @NotEmpty
    private String password;

    @Email
    @NotEmpty(message = "Email should not be null or empty")
    private String email;
}
