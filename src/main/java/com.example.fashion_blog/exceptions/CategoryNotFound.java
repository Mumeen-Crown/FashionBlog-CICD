package com.example.fashion_blog.exceptions;

public class CategoryNotFound extends RuntimeException{
    public CategoryNotFound(String message) {
        super(message);
    }
}
