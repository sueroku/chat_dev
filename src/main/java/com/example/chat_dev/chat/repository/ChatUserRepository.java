package com.example.chat_dev.chat.repository;

import com.example.exodia.chat.domain.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
}
