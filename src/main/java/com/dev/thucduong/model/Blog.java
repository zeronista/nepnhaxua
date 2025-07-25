package com.dev.thucduong.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "blogs")
@Data
public class Blog {
    @Id
    private String id;
    private String title;
    private String content;
    private String authorId;
    private String category;
    private String imageUrl; // Featured image
    private List<Comment> comments; // User comments
    private LocalDateTime createdAt; // Creation time
    private LocalDateTime updatedAt; // Last update time

    @Data
    public static class Comment {
        private String userId; // Reference to User
        private String content; // Comment text
        private LocalDateTime postedAt; // Comment time
    }
}