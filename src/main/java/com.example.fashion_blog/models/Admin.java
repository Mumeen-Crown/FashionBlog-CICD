package com.example.fashion_blog.models;


import com.example.fashion_blog.models.Post;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminId;
    private String firstName;
    private String lastName;
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDateTime createdTime;

    @JsonManagedReference
    @OneToMany(targetEntity = Post.class, mappedBy = "admin")
    private List<Post> posts;
}
