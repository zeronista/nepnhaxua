package com.dev.thucduong.dto;

import com.dev.thucduong.model.Order;
import lombok.Data;
import java.util.List;

@Data
public class OrderDTO {
    private String id;
    private String userId;
    private List<Order.OrderItem> items;
    private double total;
    private String status;
    private Order.Address shippingAddress;
    private String paymentMethod;
    private String couponCode;
}