package com.example.chat_dev.user.repository;

import com.example.exodia.common.domain.DelYN;
import com.example.exodia.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNum(String userNum);
    Optional<User> findByUserNumAndDelYn(String userNum, DelYN delYn);
    List<User> findAllByDelYn(DelYN delYn);
}
