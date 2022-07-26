package com.example.fashion_blog.controllers;


import com.example.fashion_blog.dtos.CategoryDto;
import com.example.fashion_blog.dtos.PostDto;
import com.example.fashion_blog.dtos.admindto.AdminLoginDto;
import com.example.fashion_blog.dtos.admindto.AdminSignUpDto;
import com.example.fashion_blog.models.Admin;
import com.example.fashion_blog.models.Comments;
import com.example.fashion_blog.models.Post;
import com.example.fashion_blog.service.serviceImpl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private final AdminServiceImpl adminService;

    private final HttpSession httpSession;


    public AdminController(AdminServiceImpl adminService, HttpSession httpSession) {
        this.adminService = adminService;
        this.httpSession = httpSession;
    }

    @PostMapping("/signup")
    public String createAdmin(@RequestBody AdminSignUpDto adminSignUpDto) {
        adminService.createAdmin(adminSignUpDto);
        return "Registration successful";
    }


    @PostMapping("/login")
    public Admin login(@Valid @RequestBody AdminLoginDto adminLoginDto) {
        Admin admin = adminService.login(adminLoginDto);
        httpSession.setAttribute("adminId", admin.getAdminId());
        return admin;
    }

    @GetMapping("/logout")
    public String signOut() {
        httpSession.invalidate();
        return "Successfully logged out";
    }

    @PostMapping("/create-category")
    public String createCategory(@RequestBody CategoryDto categoryDto) {
        adminService.createCategory((Long)httpSession.getAttribute("adminId"), categoryDto);
        return "Category created successfully";
    }

    @PostMapping("/upload-post")
    public String uploadPost(@RequestBody PostDto postDto) {
        adminService.uploadPost((Long) httpSession.getAttribute("adminId"), postDto);
        return "Post successfully uploaded";
    }

    @GetMapping("/fetch-all-post")
    public List<Post> fetchAllPost() {
        return adminService.fetchAllPosts((Long) httpSession.getAttribute("adminId"));
    }

    @GetMapping("/fetch-post/{postId}")
    public Post fetchPost(@PathVariable String postId) {
        return adminService.fetchPost((Long) httpSession.getAttribute("adminId"), Long.parseLong(postId));
    }

    @PostMapping("/modify-post/{postId}")
    public Post modifyPost(@RequestBody PostDto postDto, @PathVariable String postId) {
        return adminService.modifyPost((Long) httpSession.getAttribute("adminId"), Long.parseLong(postId), postDto);
    }

    @GetMapping("/delete-post/{postId}")
    public String deletePost(@PathVariable String postId) {
        adminService.deletePost((Long) httpSession.getAttribute("adminId"), Long.parseLong(postId));
        return "Post deleted successfully";
    }

    @GetMapping("/view-likes/{postId}")
    public String viewNoOfLikes(@PathVariable String postId) {
        return adminService.viewNumberOfLikes((Long) httpSession.getAttribute("adminId"), Long.parseLong(postId));
    }

    @GetMapping("/view-comments/{postId}")
    public List<Comments> viewComments(@PathVariable String postId) {
        return adminService.viewComments((Long) httpSession.getAttribute("adminId"), Long.parseLong(postId));
    }


}
