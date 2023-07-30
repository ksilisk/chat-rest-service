package com.ksilisk.chatservice.security;

public interface TokenProvider {
    String create(String subject, Long userId);
}
