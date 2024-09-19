package com.example.chat_dev.common.config;

public enum WebSocketMessageType {
    ENTER("ENTER"),
    JOIN("JOIN"),
    TALK("TALK"),
    EXIT("EXIT"),
    SUB("SUBSCRIBE"),
    PUB("PUBLISH");

    private final String type;

    WebSocketMessageType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.type;
    }
}
