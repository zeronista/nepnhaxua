package com.dev.thucduong.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private String id;
    private String productId;
    private String userId;
    private int rating;
    private String comment;
}