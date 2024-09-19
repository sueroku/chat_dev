package com.example.chat_dev.chat.component;

import com.example.chat_dev.chat.dto.ChatDto;
import com.example.chat_dev.chat.service.RedisServiceImpl;
import com.example.chat_dev.common.config.WebSocketMessage;
import com.example.chat_dev.common.config.WebSocketMessageType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@Getter
@Component
@RequiredArgsConstructor
public class ChatRoom {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RedisServiceImpl redisService;

    public void enter(ChatDto chatDto, WebSocketSession session) {
        String username = (String) session.getAttributes().get("username");
        String channel = "chatRoom:"+chatDto.getChatRoomId();
        redisService.subscribe(channel, session);

        chatDto.setMessage(username + "님이 입장하셨습니다.");
        redisService.publish(channel, getTextMessage(WebSocketMessageType.ENTER, chatDto));
    }

    public void sendMessage(ChatDto chatDto) {
        String channel = "chatRoom:"+chatDto.getChatRoomId();
        redisService.publish(channel, getTextMessage(WebSocketMessageType.TALK, chatDto));
    }

    private String getTextMessage(WebSocketMessageType type, ChatDto chatDto) {
        try {
            return objectMapper.writeValueAsString(new WebSocketMessage(type, chatDto));
        }catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
