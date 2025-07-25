package com.dev.thucduong.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;
    private String email;
    private String password; // Hashed password
    private String fullName; // e.g., "Minh Anh"
    private List<Address> addresses; // List of shipping addresses
    private List<String> orderIds; // References to Orders
    private String role; // e.g., "Customer", "Admin"
    private LocalDateTime createdAt; // Account creation time
    private LocalDateTime updatedAt; // Last update time

    @Data
    public static class Address {
        private String fullName;
        private String street;
        private String city;
        private String postalCode;
        private String phone;
    }
}