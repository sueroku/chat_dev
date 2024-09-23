package com.example.chat_dev.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDeleteDto {
    private String userNum;
    private String deletedBy;
    private String reason;
}
