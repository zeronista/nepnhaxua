package com.dev.thucduong.controller;

import com.dev.thucduong.dto.ChatMessageDTO;
import com.dev.thucduong.service.ChatMessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat-messages")
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;

    @PostMapping
    public ResponseEntity<ChatMessageDTO> saveChatMessage(@Valid @RequestBody ChatMessageDTO chatMessageDTO) {
        return ResponseEntity.status(201).body(chatMessageService.saveChatMessage(chatMessageDTO));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChatMessageDTO>> getUserChatHistory(@PathVariable String userId) {
        return ResponseEntity.ok(chatMessageService.getUserChatHistory(userId));
    }
}