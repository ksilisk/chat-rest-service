package com.ksilisk.chatservice.cleaner;

import com.ksilisk.chatservice.entity.Chat;
import com.ksilisk.chatservice.repository.ChatRepository;
import com.ksilisk.chatservice.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
class ScheduledEmptyChatsCleaner {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    @Scheduled(initialDelay = 60, fixedDelayString = "${config.cleaner.empty-chats-clean-delay:86400}",
            timeUnit = TimeUnit.SECONDS)
    private void clean() {
        log.info("Start clean empty chats");
        List<Chat> chatsForClean = chatRepository.findAllByUsersEmpty();
        chatsForClean.forEach(chat -> messageRepository.deleteAllByChatId(chat.getId()));
        chatRepository.deleteAll(chatsForClean);
        log.info("Empty chats clean successfully");
    }
}
