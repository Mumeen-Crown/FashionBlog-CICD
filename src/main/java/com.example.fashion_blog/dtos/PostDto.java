package com.example.fashion_blog.dtos;


import com.example.fashion_blog.models.Categories;
import lombok.Data;

@Data
public class PostDto {

    private String title;
    private String description;
    private String category;
}
