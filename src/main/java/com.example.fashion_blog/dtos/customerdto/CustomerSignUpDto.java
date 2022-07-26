package com.example.fashion_blog.dtos.customerdto;

import lombok.Data;

@Data
public class CustomerSignUpDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
