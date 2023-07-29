package com.ksilisk.chatservice.repository;

import com.ksilisk.chatservice.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u from User u where u.username = ?1 or u.email = ?1")
    Optional<User> findByUsernameOrEmail(String usernameOrEmail);

    Optional<User> findUserById(long id);

    Optional<User> findUserByUsername(String username);

    boolean existsByUsernameOrEmail(String username, String email);
}
