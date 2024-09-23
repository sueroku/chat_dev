package com.example.chat_dev.user.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDto {
    private String userNum;
    private String password;
}
