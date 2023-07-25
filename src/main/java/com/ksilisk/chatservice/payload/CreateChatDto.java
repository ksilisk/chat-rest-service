package com.ksilisk.chatservice.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreateChatDto {
    @NotEmpty
    private String name;

    @NotEmpty
    private Set<Long> userIds;
}
