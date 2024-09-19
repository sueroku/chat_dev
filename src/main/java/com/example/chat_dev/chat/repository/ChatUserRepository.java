package com.example.chat_dev.chat.repository;

import com.example.chat_dev.chat.domain.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
}
