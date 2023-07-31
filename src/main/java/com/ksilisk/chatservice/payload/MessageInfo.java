package com.ksilisk.chatservice.payload;

import com.ksilisk.chatservice.entity.Message;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageInfo {
    private long id;
    private String text;
    private long userId;
    private long chatId;
    private long time;

    public static MessageInfo from(Message message) {
        return new MessageInfo(message.getId(), message.getText(), message.getUser().getId(),
                message.getChat().getId(), message.getTime().getTime());
    }
}
