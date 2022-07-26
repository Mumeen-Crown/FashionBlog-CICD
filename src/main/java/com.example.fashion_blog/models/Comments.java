package com.example.fashion_blog.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    private String content;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;

    @JsonBackReference
    @ManyToOne
    private Customer customer;

    @JsonBackReference
    @ManyToOne
    private Post post;
}
