package com.dev.thucduong.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "products")
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String imageUrl; // URL to product image
    private String[] benefits;
    private int stock;
    private double discount;
    private String[] relatedBodyParts;
}