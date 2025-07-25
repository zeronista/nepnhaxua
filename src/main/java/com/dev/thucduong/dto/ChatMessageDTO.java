package com.dev.thucduong.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ChatMessageDTO {
    private String id;
    private String userId;
    private String message;
    private String response;
    private LocalDateTime timestamp;
}