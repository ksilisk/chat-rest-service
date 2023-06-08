package com.ksilisk.chatservice.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Min(value = 1)
    private Long id;

    @NotEmpty
    @Size(min = 5, message = "Username should have at least 5 characters")
    private String username;

    @Email
    @NotEmpty(message = "Email should not be null or empty")
    private String email;
}
