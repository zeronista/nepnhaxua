package com.dev.thucduong.util;

import com.dev.thucduong.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    public static <T> ResponseEntity<Map<String, Object>> successResponse(T data, String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", message);
        response.put("data", data);
        response.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<ErrorResponse> errorResponse(String error, String message, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(status.value(), error, message);
        return new ResponseEntity<>(errorResponse, status);
    }
}