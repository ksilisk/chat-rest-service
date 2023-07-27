package com.ksilisk.chatservice.repository;

import com.ksilisk.chatservice.entity.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {
    Optional<Chat> findChatById(long id);

     List<Chat> findAllByUsersEmpty();
}
