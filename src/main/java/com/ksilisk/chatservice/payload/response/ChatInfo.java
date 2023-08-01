package com.ksilisk.chatservice.payload.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ChatInfo {
    private long id;
    @NotEmpty
    private String name;
    @NotNull
    private UserInfo owner;
    @NotNull
    private List<UserInfo> users;
    @NotNull
    private List<MessageInfo> messages;

    public ChatInfo(long id, String name, UserInfo owner) {
        this(id, name, owner, Collections.emptyList(), Collections.emptyList());
    }
}
