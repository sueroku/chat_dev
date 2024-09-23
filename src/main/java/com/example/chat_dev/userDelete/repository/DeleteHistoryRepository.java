package com.example.chat_dev.userDelete.repository;

import com.example.exodia.userDelete.domain.DeleteHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeleteHistoryRepository extends JpaRepository<DeleteHistory, Long> {
}
