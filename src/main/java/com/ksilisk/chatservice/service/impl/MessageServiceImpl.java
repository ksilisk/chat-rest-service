package com.ksilisk.chatservice.service.impl;

import com.ksilisk.chatservice.repository.MessageRepository;
import com.ksilisk.chatservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
}
