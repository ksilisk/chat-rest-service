package com.ksilisk.chatservice.payload.response;

import com.ksilisk.chatservice.entity.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfo {
    private long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;

    public static UserInfo from(User user) {
        return new UserInfo(user.getId(), user.getUsername(), user.getEmail());
    }
}
