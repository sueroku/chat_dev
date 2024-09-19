package com.example.chat_dev.chat.repository;

import com.example.chat_dev.chat.domain.ChatFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatFileRepository extends JpaRepository<ChatFile, Long> {
}
