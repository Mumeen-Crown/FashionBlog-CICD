package com.example.fashion_blog.dtos.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String debugMessage;
}
