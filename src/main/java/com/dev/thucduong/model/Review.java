package com.dev.thucduong.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;

@Document(collection = "reviews")
@Data
public class Review {
    @Id
    private String id;
    private String productId; // Reference to Product
    private String userId; // Reference to User
    private int rating; // 1 to 5 stars
    private String comment;
    private LocalDateTime createdAt; // Review creation time
    private LocalDateTime updatedAt; // Last update time
}