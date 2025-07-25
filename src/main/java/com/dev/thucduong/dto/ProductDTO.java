package com.dev.thucduong.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String imageUrl;
    private String[] benefits;
    private double discount;
}