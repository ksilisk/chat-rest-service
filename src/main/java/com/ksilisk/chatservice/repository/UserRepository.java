package com.ksilisk.chatservice.repository;

import com.ksilisk.chatservice.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsernameOrEmail(String username, String email);

    Optional<User> findUserById(long id);

    Optional<User> findUserByUsername(String username);
}
