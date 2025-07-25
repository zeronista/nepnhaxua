package com.dev.thucduong.util;

public class Constants {

    // Roles
    public static final String ROLE_CUSTOMER = "CUSTOMER";
    public static final String ROLE_ADMIN = "ADMIN";

    // API Paths
    public static final String API_PREFIX = "/api";
    public static final String PRODUCTS_PATH = API_PREFIX + "/products";
    public static final String ORDERS_PATH = API_PREFIX + "/orders";
    public static final String BLOGS_PATH = API_PREFIX + "/blogs";
    public static final String USERS_PATH = API_PREFIX + "/users";
    public static final String REVIEWS_PATH = API_PREFIX + "/reviews";
    public static final String CHAT_MESSAGES_PATH = API_PREFIX + "/chat-messages";

    // Error Messages
    public static final String ERROR_RESOURCE_NOT_FOUND = "Resource not found: %s";
    public static final String ERROR_VALIDATION = "Invalid request data";
    public static final String ERROR_INTERNAL_SERVER = "Internal server error";

    // Success Messages
    public static final String SUCCESS_CREATED = "Resource created successfully";
    public static final String SUCCESS_UPDATED = "Resource updated successfully";
    public static final String SUCCESS_DELETED = "Resource deleted successfully";

    // Product Categories
    public static final String CATEGORY_BROWN_RICE = "Gạo lứt";
    public static final String CATEGORY_NODDLES = "Bún lứt";
    public static final String CATEGORY_HERBAL_TEA = "Trà thảo mộc";
    public static final String CATEGORY_COMBO = "Combo thực dưỡng";

    // Body Parts for Human Body Map
    public static final String BODY_PART_DIGESTIVE = "Digestive System";
    public static final String BODY_PART_HEART = "Heart";
    public static final String BODY_PART_LIVER = "Liver";
}