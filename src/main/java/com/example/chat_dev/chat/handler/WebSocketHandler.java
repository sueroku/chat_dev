package com.example.chat_dev.chat.handler;

import com.example.chat_dev.chat.component.ChatRoom;
import com.example.chat_dev.chat.dto.ChatDto;
import com.example.chat_dev.common.config.WebSocketMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler{

    private final ChatRoom chatRoom;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws JsonProcessingException {
        String username = (String) session.getAttributes().get("username");
        WebSocketMessage webSocketMessage = (WebSocketMessage) objectMapper.readValue(message.getPayload(), WebSocketMessage.class);
        switch (webSocketMessage.getType().getValue()) {
            case "ENTER":
                enterChatRoom(webSocketMessage.getPayload(), session);
                break;
            case "TALK":
                sendMessage(username, webSocketMessage.getPayload());
                break;
            default:
                System.out.println("no case");
                break;
        }
//        switch (webSocketMessage.getType().getValue()) {
//            case "ENTER" -> enterChatRoom(webSocketMessage.getPayload(), session);
//            case "TALK" -> sendMessage(username, webSocketMessage.getPayload());
//        } // JAVA 14
    }

    private void sendMessage(String username, ChatDto chatDto) {
        log.info("send chatDto : " + chatDto.toString());
        chatRoom.sendMessage(chatDto);
    }

    private void enterChatRoom(ChatDto chatDto, WebSocketSession session) {
        log.info("enter chatDto : " + chatDto.toString());
        chatRoom.enter(chatDto, session);
    }
}
