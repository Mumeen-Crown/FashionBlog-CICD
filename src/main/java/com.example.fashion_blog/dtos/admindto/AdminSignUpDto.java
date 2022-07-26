package com.example.fashion_blog.dtos.admindto;

import lombok.Data;

@Data
public class AdminSignUpDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
