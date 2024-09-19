package com.example.chat_dev.chat.service;

import com.example.chat_dev.common.config.WebSocketMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl {
    private final StringRedisTemplate stringRedisTemplate;

    public void publish(String channel, String message) {
        stringRedisTemplate.convertAndSend(channel, message);
    }

    public void subscribe(String channel, WebSocketSession session) {
        Objects.requireNonNull(stringRedisTemplate.getConnectionFactory())
                .getConnection()
                .subscribe(getMessageHandler(session), channel.getBytes());
    }

    public void subscribe(String[] channel, WebSocketSession session) {
        for (String c : channel){
            Objects.requireNonNull(stringRedisTemplate.getConnectionFactory())
                    .getConnection()
                    .subscribe(getMessageHandler(session), c.getBytes());
        }
    }

    private RedisMessageHandler getMessageHandler(WebSocketSession session) {
        return new RedisMessageHandler(session);
    }
}

@Slf4j
@RequiredArgsConstructor
class RedisMessageHandler implements MessageListener {
    private final WebSocketSession session;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            WebSocketMessage webSocketMessage = objectMapper.readValue(message.getBody(), WebSocketMessage.class);
            if(session.isOpen() && !webSocketMessage.getPayload().getUsername().equals(session.getAttributes().get("username"))){
                session.sendMessage(new TextMessage(new String(message.getBody())));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}