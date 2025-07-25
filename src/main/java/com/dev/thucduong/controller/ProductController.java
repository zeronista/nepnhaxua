package com.dev.thucduong.controller;

import com.dev.thucduong.dto.ProductDTO;
import com.dev.thucduong.service.ProductService;
import com.dev.thucduong.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseUtil.successResponse(products, "Products retrieved successfully", HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam String query) {
        return ResponseEntity.ok(productService.searchProducts(query));
    }

    @GetMapping("/body-part/{bodyPart}")
    public ResponseEntity<List<ProductDTO>> getProductsByBodyPart(@PathVariable String bodyPart) {
        return ResponseEntity.ok(productService.getProductsByBodyPart(bodyPart));
    }
}