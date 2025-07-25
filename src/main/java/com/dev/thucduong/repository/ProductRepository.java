package com.dev.thucduong.repository;


import com.dev.thucduong.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategory(String category);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByRelatedBodyPartsContaining(String bodyPart);
}