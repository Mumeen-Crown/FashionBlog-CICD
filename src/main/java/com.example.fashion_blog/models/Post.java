package com.example.fashion_blog.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;
    private String title;
    private String imageUrl;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;

    @JsonManagedReference
    @OneToMany(targetEntity = Comments.class, mappedBy = "post")
    private List<Comments> comments;

    @JsonBackReference
    @ManyToOne
    private Admin admin;

    @JsonBackReference
    @ManyToOne
    private Categories category;

    @JsonManagedReference
    @OneToMany(targetEntity = Likes.class, mappedBy = "post")
    private List<Likes> likes;


}
