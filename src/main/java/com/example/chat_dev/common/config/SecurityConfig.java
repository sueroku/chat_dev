package com.example.chat_dev.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/ws/chats/**").permitAll()  // WebSocket URL에 대한 인증 해제
                .anyRequest().authenticated()
                .and()
                .csrf().disable();  // WebSocket 연결 시 CSRF 비활성화

        return http.build();  // SecurityFilterChain 반환
    }

}
