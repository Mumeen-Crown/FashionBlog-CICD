package com.example.fashion_blog.service.serviceImpl;

import com.example.fashion_blog.dtos.CommentsDto;
import com.example.fashion_blog.models.Customer;
import com.example.fashion_blog.dtos.customerdto.CustomerLoginDto;
import com.example.fashion_blog.dtos.customerdto.CustomerSignUpDto;
import com.example.fashion_blog.exceptions.CommentNotFoundException;
import com.example.fashion_blog.exceptions.PostNotFoundException;
import com.example.fashion_blog.exceptions.ResourceAlreadyExistException;
import com.example.fashion_blog.exceptions.ResourceNotFoundException;
import com.example.fashion_blog.models.*;
import com.example.fashion_blog.repositories.*;
import com.example.fashion_blog.service.CustomerService;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final PostRepository postRepository;

    private final CategoryRepository categoryRepository;

    private final CommentsRepository commentsRepository;

    private final LikesRepository likesRepository;


    public CustomerServiceImpl(CustomerRepository customerRepository, PostRepository postRepository,
                               CategoryRepository categoryRepository, CommentsRepository commentsRepository,
                               LikesRepository likesRepository) {
        this.customerRepository = customerRepository;
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.commentsRepository = commentsRepository;
        this.likesRepository = likesRepository;
    }

    @Override
    public void createUser(CustomerSignUpDto customerSignUpDto) {
        Optional<Customer> dbCustomer = customerRepository.findByEmail(customerSignUpDto.getEmail());
        if (dbCustomer.isPresent()) {
            throw new ResourceAlreadyExistException("User with the email; " + customerSignUpDto.getEmail()
            + ", already exists");
        }
        Customer customer = new Customer();
        customer.setFirstName(customerSignUpDto.getFirstName());
        customer.setLastName(customerSignUpDto.getLastName());
        customer.setEmail(customerSignUpDto.getEmail());
        customer.setPassword(customerSignUpDto.getPassword());
        customer.setCreatedTime(LocalDateTime.now());

        customerRepository.save(customer);
    }

    @Override
    public Customer login(CustomerLoginDto customerLoginDto) {
        return customerRepository.findByEmailAndPassword(customerLoginDto.getEmail(), customerLoginDto.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
    }

    @Override
    public List<Post> viewAllPosts(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
        return postRepository.findAll();
    }

    @Override
    public Post viewPost(Long customerId, Long postId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(""));

        return post;
    }

    @Override
    public Post postComment(Long customerId, Long postId, CommentsDto commentsDto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(""));
        Comments comment = new Comments();
        comment.setContent(commentsDto.getComment());
        comment.setCustomer(customer);
        comment.setPost(post);
        comment.setDateCreated(LocalDateTime.now());
        commentsRepository.save(comment);

        return post;
    }

    @Override
    public Likes likePost(Long customerId, Long postId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(""));
        Likes like = new Likes();
        like.setCustomer(customer);
        like.setPost(post);
        like.setQuantity(post.getLikes().size() + 1);
        likesRepository.save(like);
//        int numberOfLikes = post.getLikes().size();

//        return numberOfLikes + " Likes";
        return like;
    }

    @Override
    public List<Comments> viewAllComments(Long customerId, Long postId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("User does not exist"));
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new PostNotFoundException("Post does not exist"));
        List<Comments> comments = post.getComments();
        if (comments.size() == 0) {
            throw new CommentNotFoundException("No comment on the post yet");
        }
        return comments;
    }

//    @Override
//    public List<Post> showPostByCategory(Long categoryId) {
//
//        return null;
//    }
}
