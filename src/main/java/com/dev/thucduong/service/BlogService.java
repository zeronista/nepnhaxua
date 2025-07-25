package com.dev.thucduong.service;

import com.dev.thucduong.dto.BlogDTO;
import com.dev.thucduong.model.Blog;
import com.dev.thucduong.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public BlogDTO createBlog(BlogDTO blogDTO) {
        Blog blog = new Blog();
        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        blog.setAuthorId(blogDTO.getAuthorId());
        blog.setCategory(blogDTO.getCategory());
        blog.setImageUrl(blogDTO.getImageUrl());
        blog.setCreatedAt(LocalDateTime.now());
        blog.setUpdatedAt(LocalDateTime.now());
        Blog savedBlog = blogRepository.save(blog);
        return convertToDTO(savedBlog);
    }

    public List<BlogDTO> getBlogsByCategory(String category) {
        return blogRepository.findByCategory(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BlogDTO convertToDTO(Blog blog) {
        BlogDTO dto = new BlogDTO();
        dto.setId(blog.getId());
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        dto.setAuthorId(blog.getAuthorId());
        dto.setCategory(blog.getCategory());
        dto.setImageUrl(blog.getImageUrl());
        dto.setComments(blog.getComments());
        return dto;
    }
}