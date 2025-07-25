package com.dev.thucduong.repository;

import com.dev.thucduong.model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface BlogRepository extends MongoRepository<Blog, String> {
    List<Blog> findByCategory(String category);
    List<Blog> findByAuthorId(String authorId);
}