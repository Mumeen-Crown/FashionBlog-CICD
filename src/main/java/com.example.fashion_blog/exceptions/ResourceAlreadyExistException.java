package com.example.fashion_blog.exceptions;

public class ResourceAlreadyExistException extends  RuntimeException{

    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}
