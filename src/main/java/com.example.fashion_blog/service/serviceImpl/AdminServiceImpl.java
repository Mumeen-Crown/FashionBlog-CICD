package com.example.fashion_blog.service.serviceImpl;

import com.example.fashion_blog.dtos.CategoryDto;
import com.example.fashion_blog.dtos.PostDto;
import com.example.fashion_blog.dtos.admindto.AdminLoginDto;
import com.example.fashion_blog.dtos.admindto.AdminSignUpDto;
import com.example.fashion_blog.exceptions.*;
import com.example.fashion_blog.models.*;
import com.example.fashion_blog.repositories.AdminRepository;
import com.example.fashion_blog.repositories.CategoryRepository;
import com.example.fashion_blog.repositories.PostRepository;
import com.example.fashion_blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private final CategoryRepository categoryRepository;

    public AdminServiceImpl(AdminRepository adminRepository, PostRepository postRepository, CategoryRepository categoryRepository) {
        this.adminRepository = adminRepository;
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createAdmin(AdminSignUpDto adminSignUpDto) {
        Optional<Admin> dbAdmin = adminRepository.findByEmail(adminSignUpDto.getEmail());
        if (dbAdmin.isPresent()) {
            throw new ResourceAlreadyExistException("User with the email; " + adminSignUpDto.getEmail() + ", already exist" );
        }
        Admin admin = new Admin();
        admin.setFirstName(adminSignUpDto.getFirstName());
        admin.setLastName(adminSignUpDto.getLastName());
        admin.setEmail(adminSignUpDto.getEmail());
        admin.setPassword(adminSignUpDto.getPassword());
        admin.setCreatedTime(LocalDateTime.now());

        adminRepository.save(admin);
    }

    @Override
    public Admin login(AdminLoginDto adminLoginDto) {
        return adminRepository.findByEmailAndPassword(adminLoginDto.getEmail(), adminLoginDto.getPassword())
                .orElseThrow(()-> new ResourceNotFoundException("Invalid email or password"));
    }

    @Override
    public void createCategory(Long adminId, CategoryDto categoryDto) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("You are not allowed to perform this operation"));
         Categories category = categoryRepository.findByCategoryName(categoryDto.getCategoryName());
         if (Objects.nonNull(category)) {
             throw new CategoryAlreadyExistsException("Category already exists");
         }
         Categories newCategory = new Categories();
         newCategory.setCategoryName(categoryDto.getCategoryName());

         categoryRepository.save(newCategory);
    }

    @Override
    public Post uploadPost(Long adminId, PostDto postDto) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(()-> new ResourceNotFoundException("User does not exist"));
        Categories category = categoryRepository.findByCategoryName(postDto.getCategory());
        if (Objects.isNull(category)) {
            throw new CategoryNotFound("category; " + postDto.getCategory() + ", does not exist");
        }
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setDateCreated(LocalDateTime.now());
        post.setAdmin(admin);
        post.setCategory(category);

        return postRepository.save(post);
    }

    @Override
    public List<Post> fetchAllPosts(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(()-> new ResourceNotFoundException("User does not exist"));
        List<Post> allPost = postRepository.findAll();
        if(allPost.size() == 0) {
            throw new PostNotFoundException("No post added yet");
        }
        return allPost;

    }

    @Override
    public Post fetchPost(Long adminId, Long postId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(()-> new ResourceNotFoundException("User does not exist"));
            Optional<Post> post = postRepository.findById(postId);
            return post
                    .orElseThrow(()-> new PostNotFoundException("Post not added yet"));

    }


    @Override
    public Post modifyPost(Long adminId, Long postId, PostDto postDto) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(()-> new ResourceNotFoundException("User does not exist"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post does not exist"));
        Categories category = categoryRepository.findByCategoryName(postDto.getCategory());
        if (Objects.isNull(category)) {
            throw new CategoryNotFound("category; " + postDto.getCategory() + ", does not exist");
        }
        post.setAdmin(admin);
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setDateModified(LocalDateTime.now());
        post.setCategory(category);
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long adminId, Long postId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceAlreadyExistException("User does not exist"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post does not exist"));
        postRepository.delete(post);
    }

    @Override
    public String viewNumberOfLikes(Long adminId, Long postId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(()-> new ResourceNotFoundException("User does not exist"));
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new PostNotFoundException("Post does not exist"));
        int numberOfLikes = post.getLikes().size();
        return numberOfLikes + " Likes";
    }

    @Override
    public List<Comments> viewComments(Long adminId, Long postId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(()-> new ResourceNotFoundException("User does not exist"));
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new PostNotFoundException("Post does not exist"));
        List<Comments> comments = post.getComments();
        if (comments.size() == 0) {
            throw new CommentNotFoundException("No comment on the post yet");
        }
        return comments;
    }
}
