package com.ksilisk.chatservice.entity;

import com.ksilisk.chatservice.repository.ChatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ChatTest {
    @Autowired
    private ChatRepository chatRepository;

    @Test
    void emptyTable_shouldBeEmpty() {
        assertFalse(chatRepository.findAll().iterator().hasNext());
    }

    @Test
    void createOneChatInstance_shouldHasOneRow() {
        // given
        String name = "LovelyName";
        Chat chat = new Chat();
        chat.setName(name);
        // when
        chatRepository.save(chat);
        // then
        assertEquals(chatRepository.count(), 1L);
        assertEquals(chatRepository.findChatById(1L).get().getName(), name);
    }

    @Test
    void createTwoChatInstance_shouldHasTwoRow() {
        // given
        String name1 = "SuperName";
        String name2 = "PrettyName";
        Chat chat1 = new Chat();
        chat1.setName(name1);
        Chat chat2 = new Chat();
        chat2.setName(name2);
        // when
        chatRepository.saveAll(Arrays.asList(chat1, chat2));
        // then
        assertEquals(chatRepository.count(), 2L);
    }
}