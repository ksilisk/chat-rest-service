package com.ksilisk.chatservice.service.impl;

import com.ksilisk.chatservice.entity.Chat;
import com.ksilisk.chatservice.entity.User;
import com.ksilisk.chatservice.exception.ChatNotFoundException;
import com.ksilisk.chatservice.exception.UserNotFoundException;
import com.ksilisk.chatservice.payload.request.CreateChatDto;
import com.ksilisk.chatservice.payload.response.ChatInfo;
import com.ksilisk.chatservice.payload.response.MessageInfo;
import com.ksilisk.chatservice.payload.response.UserInfo;
import com.ksilisk.chatservice.repository.ChatRepository;
import com.ksilisk.chatservice.repository.MessageRepository;
import com.ksilisk.chatservice.repository.UserRepository;
import com.ksilisk.chatservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    public Set<ChatInfo> getChats(JwtAuthenticationToken jwtToken) {
        User user = userRepository.findUserByUsername(jwtToken.getName())
                .orElseThrow(UserNotFoundException::new);
        return user.getChats().stream()
                .map(chat -> new ChatInfo(chat.getId(), chat.getName(), UserInfo.from(chat.getOwner())))
                .collect(toUnmodifiableSet());
    }

    @Override
    public ChatInfo createChat(CreateChatDto chatDto, JwtAuthenticationToken jwtToken) {
        User owner = userRepository.findUserByUsername(jwtToken.getName())
                .orElseThrow(UserNotFoundException::new);
        Set<User> users = chatDto.getUserIds().stream()
                .map(id -> userRepository.findUserById(id)
                        .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found")))
                .collect(toUnmodifiableSet());
        Chat newChat = Chat.builder()
                .name(chatDto.getName())
                .users(users)
                .owner(owner).build();
        long chatId = chatRepository.save(newChat).getId();
        return new ChatInfo(chatId, newChat.getName(), UserInfo.from(newChat.getOwner()));
    }

    @Override
    public void deleteChat(long id, JwtAuthenticationToken jwtToken) {
        Chat chat = chatRepository.findChatById(id)
                .orElseThrow(ChatNotFoundException::new);
        User user = userRepository.findUserByUsername(jwtToken.getName())
                .orElseThrow(UserNotFoundException::new);
        chat.getUsers().remove(user);
        setNewOwnerIfNeed(chat);
        chatRepository.save(chat);
    }

    @Override
    public ChatInfo getChat(long id, JwtAuthenticationToken authenticationToken) {
        Chat chat = chatRepository.findChatById(id)
                .orElseThrow(ChatNotFoundException::new);
        List<MessageInfo> messages = messageRepository.findAllByChatId(id).stream()
                .map(MessageInfo::from)
                .toList();
        List<UserInfo> users = chat.getUsers().stream()
                .map(UserInfo::from)
                .toList();
        return new ChatInfo(chat.getId(), chat.getName(), UserInfo.from(chat.getOwner()), users, messages);
    }

    private void setNewOwnerIfNeed(Chat chat) {
        if (!chat.getUsers().contains(chat.getOwner()) && chat.getUsers().size() > 0) {
            User newOwner = chat.getUsers().stream()
                    .findAny()
                    .orElseThrow(() -> new UserNotFoundException("New owner not found"));
            chat.setOwner(newOwner);
        }
    }
}
