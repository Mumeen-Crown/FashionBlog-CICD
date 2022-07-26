package com.example.fashion_blog.controllers;


import com.example.fashion_blog.dtos.response.ErrorResponse;
import com.example.fashion_blog.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler({ResourceAlreadyExistException.class})
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistException(final ResourceAlreadyExistException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDebugMessage("Please sign up with another email");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }


    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(final ResourceNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDebugMessage("Please check the details and try again");
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler({CategoryAlreadyExistsException.class})
    public ResponseEntity<ErrorResponse> handleCategoryAlreadyExistsException(final CategoryAlreadyExistsException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDebugMessage("Create another category with a different name");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }


    @ExceptionHandler({CategoryNotFound.class})
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(final CategoryNotFound exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDebugMessage("Create a new category in the new category page");
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }


    @ExceptionHandler({PostNotFoundException.class})
    public ResponseEntity<ErrorResponse> handlePostNotFoundException(final PostNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDebugMessage("The post is yet to be created");
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }


    @ExceptionHandler({CommentNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleCommentNotFoundException(final CommentNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDebugMessage("");
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
