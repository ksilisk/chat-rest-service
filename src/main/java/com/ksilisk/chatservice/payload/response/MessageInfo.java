package com.ksilisk.chatservice.payload.response;

import com.ksilisk.chatservice.entity.Message;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageInfo {
    private long id;
    @NotEmpty
    private String text;
    @NotNull
    private UserInfo user;
    private long chatId;
    private long time;

    public static MessageInfo from(Message message) {
        return new MessageInfo(message.getId(), message.getText(), UserInfo.from(message.getUser()),
                message.getChat().getId(), message.getTime().getTime());
    }
}
