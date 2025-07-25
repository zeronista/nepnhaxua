package com.dev.thucduong.controller;

import com.dev.thucduong.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private JwtUtil jwtUtil;

    // Public endpoint to test JWT generation
    @PostMapping("/login")
    public ResponseEntity<?> testLogin(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Simple test credentials
        if ("test@example.com".equals(email) && "password".equals(password)) {
            String token = jwtUtil.generateToken(email, "CUSTOMER");

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("message", "Login successful");
            response.put("user", email);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
    }

    // Protected endpoint to test JWT validation
    @GetMapping("/protected")
    public ResponseEntity<?> testProtected() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Access granted to protected resource");
        response.put("user", auth.getName());
        response.put("authorities", auth.getAuthorities());

        return ResponseEntity.ok(response);
    }

    // Public endpoint for health check
    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(Map.of("status", "JWT system is working"));
    }
}