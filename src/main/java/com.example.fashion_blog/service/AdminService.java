package com.example.fashion_blog.service;

import com.example.fashion_blog.dtos.CategoryDto;
import com.example.fashion_blog.dtos.PostDto;
import com.example.fashion_blog.dtos.admindto.AdminLoginDto;
import com.example.fashion_blog.dtos.admindto.AdminSignUpDto;
import com.example.fashion_blog.models.Admin;
import com.example.fashion_blog.models.Comments;
import com.example.fashion_blog.models.Post;

import java.util.List;

public interface AdminService {
    void createAdmin(AdminSignUpDto adminSignUpDto);
    Admin login(AdminLoginDto adminLoginDto);
    Post uploadPost(Long adminId, PostDto postDto);
    List<Post> fetchAllPosts(Long adminId);
    Post fetchPost(Long adminId, Long postId);
    void createCategory(Long adminId, CategoryDto categoryDto);
    Post modifyPost(Long adminId, Long postId, PostDto postDto);
    void deletePost(Long adminId, Long postId);
    String viewNumberOfLikes(Long adminId, Long postId);
    List<Comments> viewComments(Long adminId, Long postId);
}
