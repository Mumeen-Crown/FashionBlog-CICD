package com.example.fashion_blog.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likeId;
    private int quantity;

    @JsonBackReference
    @ManyToOne
    private Post post;

    @JsonBackReference
    @ManyToOne
    private Customer customer;
}
