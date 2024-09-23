package com.example.chat_dev.common.config;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub"); // sub으로 들어오는 요청을 처리해주기 위해 추가함
        config.setApplicationDestinationPrefixes("/pub"); // pub으로 들어오는 요청을 처리해주기 위해 추가함
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("*").withSockJS(); // Endpoint를 지정해주었고 setAllowedOriginPatterns("*")를 이용해서 요청 url을 전부 허용해주었다. + withSockJs() 함수를 통해 ws, wss로 socket을 연결하는 것이 아닌 http, https로 socket을 연결하도록 바꾸어주었다.
    }
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(50 * 1024 * 1024); // 메세지 크기 제한 오류 방지(이 코드가 없으면 byte code를 보낼때 소켓 연결이 끊길 수 있음)
    }
    @EventListener
    public void connectEvent(SessionConnectEvent sessionConnectEvent){
        System.out.println(sessionConnectEvent);
        System.out.println("연결 성공 감지!");
        //return "redirect:chat/message";
    }
    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        System.out.println(sessionDisconnectEvent.getSessionId());
        System.out.println("연결 끊어짐 감지!");
    }

}
