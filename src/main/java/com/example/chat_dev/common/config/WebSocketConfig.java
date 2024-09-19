package com.example.chat_dev.common.config;

import com.example.chat_dev.chat.component.WebSocketAuthInterceptor;
import com.example.chat_dev.chat.handler.WebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;
    private final WebSocketAuthInterceptor webSocketAuthInterceptor;

    public WebSocketConfig(WebSocketHandler webSocketHandler, WebSocketAuthInterceptor webSocketAuthInterceptor) {
        this.webSocketHandler = webSocketHandler;
        this.webSocketAuthInterceptor = webSocketAuthInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws/chats")
                .setAllowedOrigins("*")
                .addInterceptors(webSocketAuthInterceptor);
//                .addInterceptors(new HttpSessionHandshakeInterceptor());
    }
}
