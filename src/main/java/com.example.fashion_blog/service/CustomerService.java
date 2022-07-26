package com.example.fashion_blog.service;

import com.example.fashion_blog.dtos.CommentsDto;
import com.example.fashion_blog.dtos.customerdto.CustomerLoginDto;
import com.example.fashion_blog.dtos.customerdto.CustomerSignUpDto;
import com.example.fashion_blog.models.Comments;
import com.example.fashion_blog.models.Customer;
import com.example.fashion_blog.models.Likes;
import com.example.fashion_blog.models.Post;

import java.util.List;

public interface CustomerService {

    void createUser(CustomerSignUpDto customerSignUpDto);
    Customer login(CustomerLoginDto customerLoginDto);
    List<Post> viewAllPosts(Long customerId);
    Post viewPost(Long customerId, Long postId);
    Post postComment(Long customerId, Long postId, CommentsDto commentsDto);
    Likes likePost(Long customerId, Long postId);
    List<Comments> viewAllComments(Long customerId, Long postId);
//    List<Post> showPostByCategory(Long categoryId);
}
