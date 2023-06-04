package com.ksilisk.chatservice.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    private Long id;

    @NotEmpty
    @Size(min = 5, message = "Username should have at least 5 characters")
    private String username;

    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;
}
