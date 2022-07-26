package com.example.fashion_blog.controllers;


import com.example.fashion_blog.dtos.CommentsDto;
import com.example.fashion_blog.dtos.customerdto.CustomerLoginDto;
import com.example.fashion_blog.dtos.customerdto.CustomerSignUpDto;
import com.example.fashion_blog.models.Comments;
import com.example.fashion_blog.models.Customer;
import com.example.fashion_blog.models.Likes;
import com.example.fashion_blog.models.Post;
import com.example.fashion_blog.service.serviceImpl.CustomerServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

    private final CustomerServiceImpl customerService;

    private final HttpSession httpSession;

    public CustomerController(CustomerServiceImpl customerService, HttpSession httpSession) {
        this.customerService = customerService;
        this.httpSession = httpSession;
    }

    @PostMapping("/sign-up")
    public String createUser(@RequestBody CustomerSignUpDto customerSignUpDto) {
        customerService.createUser(customerSignUpDto);
        return "Registration successful";
    }

    @PostMapping("/login")
    public Customer login(@Valid @RequestBody CustomerLoginDto customerLoginDto) {
        Customer customer = customerService.login(customerLoginDto);
        httpSession.setAttribute("customerId", customer.getCustomerId());
        return customer;
    }

    @GetMapping("/logout")
    public String logout() {
        httpSession.invalidate();
        return "Successfully logged out";
    }

    @GetMapping("/view-all-posts")
    public List<Post> viewAllPost() {
       return customerService.viewAllPosts((Long) httpSession.getAttribute("customerId"));
    }


    @GetMapping("/view-post/{postId}")
    public Post viewPost(@PathVariable String postId) {
        return customerService.viewPost((Long) httpSession.getAttribute("customerId"), Long.parseLong(postId));
    }

    @PostMapping("/post-comment/{postId}")
    public Post postComment(@RequestBody CommentsDto commentsDto, @PathVariable String postId) {
        return customerService.postComment((Long) httpSession.getAttribute("customerId"),
                Long.parseLong(postId), commentsDto);
    }


    @GetMapping("/like-post/{postId}")
    public Likes likePost(@PathVariable String postId) {
        return customerService.likePost((Long) httpSession.getAttribute("customerId"), Long.parseLong(postId));
    }

    @GetMapping("/view-all-comments/{postId}")
    public List<Comments> viewAllComments(@PathVariable String postId) {
        return customerService.viewAllComments((Long) httpSession.getAttribute("customerId"), Long.parseLong(postId));
    }
}
