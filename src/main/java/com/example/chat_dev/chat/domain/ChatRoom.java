package com.example.chat_dev.chat.domain;

import com.example.chat_dev.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Where(clause = "del_yn = 'N'")
public class ChatRoom extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 채팅방 고유의 id

    @Column(nullable = false)
    private String roomName;

    @OneToMany(mappedBy = "chat_room", cascade = CascadeType.ALL)
    private List<ChatUser> chatUsers = new ArrayList<>();

    @OneToMany(mappedBy = "chat_room", cascade = CascadeType.ALL)
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @OneToMany(mappedBy = "chat_room", cascade = CascadeType.ALL)
    private List<ChatFile> chatFiles = new ArrayList<>();

}
