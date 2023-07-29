package com.ksilisk.chatservice.repository;

import com.ksilisk.chatservice.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    Optional<Message> findMessageById(long id);

    List<Message> findAllByChatId(long chatId);

    void deleteAllByChatId(long chatId);
}
