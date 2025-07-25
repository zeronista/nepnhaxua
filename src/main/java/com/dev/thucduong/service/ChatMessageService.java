package com.dev.thucduong.service;

import com.dev.thucduong.dto.ChatMessageDTO;
import com.dev.thucduong.model.ChatMessage;
import com.dev.thucduong.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessageDTO saveChatMessage(ChatMessageDTO chatMessageDTO) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUserId(chatMessageDTO.getUserId());
        chatMessage.setMessage(chatMessageDTO.getMessage());
        chatMessage.setResponse(chatMessageDTO.getResponse());
        chatMessage.setTimestamp(LocalDateTime.now());
        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);
        return convertToDTO(savedMessage);
    }

    public List<ChatMessageDTO> getUserChatHistory(String userId) {
        return chatMessageRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ChatMessageDTO convertToDTO(ChatMessage chatMessage) {
        ChatMessageDTO dto = new ChatMessageDTO();
        dto.setId(chatMessage.getId());
        dto.setUserId(chatMessage.getUserId());
        dto.setMessage(chatMessage.getMessage());
        dto.setResponse(chatMessage.getResponse());
        dto.setTimestamp(chatMessage.getTimestamp());
        return dto;
    }
}