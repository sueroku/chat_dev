package com.example.chat_dev.common.config;

import com.example.chat_dev.chat.dto.ChatDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WebSocketMessage {
    private final WebSocketMessageType type;
    private final ChatDto payload;

    @JsonCreator
    public WebSocketMessage(
            @JsonProperty("type") WebSocketMessageType type,
            @JsonProperty("payload") ChatDto payload) {
        this.type = type;
        this.payload = payload;
    }
}
