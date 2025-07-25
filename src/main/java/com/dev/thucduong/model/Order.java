package com.dev.thucduong.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Data
public class Order {
    @Id
    private String id;
    private String userId; // Reference to User
    private List<OrderItem> items; // List of products and quantities
    private double total; // Total price after discounts
    private String status; // e.g., "Pending", "Shipped", "Delivered"
    private Address shippingAddress; // Delivery address
    private String paymentMethod; // e.g., "COD", "Bank Transfer"
    private String couponCode; // e.g., for 25% off
    private LocalDateTime createdAt; // Order creation time
    private LocalDateTime updatedAt; // Last update time

    @Data
    public static class OrderItem {
        private String productId; // Reference to Product
        private int quantity; // Number of units
        private double unitPrice; // Price per unit at purchase
    }

    @Data
    public static class Address {
        private String fullName;
        private String street;
        private String city;
        private String postalCode;
        private String phone;
    }
}