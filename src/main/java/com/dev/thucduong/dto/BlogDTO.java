package com.dev.thucduong.dto;

import com.dev.thucduong.model.Blog;
import lombok.Data;
import java.util.List;

@Data
public class BlogDTO {
    private String id;
    private String title;
    private String content;
    private String authorId;
    private String category;
    private String imageUrl;
    private List<Blog.Comment> comments;
}