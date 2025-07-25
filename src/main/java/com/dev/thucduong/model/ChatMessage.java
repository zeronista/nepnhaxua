package com.dev.thucduong.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;

@Document(collection = "chat_messages")
@Data
public class ChatMessage {
    @Id
    private String id;
    private String userId; // Reference to User (null for guests)
    private String message; // User’s question, e.g., "Gạo lứt có lợi ích gì?"
    private String response; // Chatbot’s reply
    private LocalDateTime timestamp; // Message time
}