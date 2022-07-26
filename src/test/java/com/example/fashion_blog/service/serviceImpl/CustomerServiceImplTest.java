package com.example.fashion_blog.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.fashion_blog.dtos.CommentsDto;
import com.example.fashion_blog.dtos.customerdto.CustomerLoginDto;
import com.example.fashion_blog.dtos.customerdto.CustomerSignUpDto;
import com.example.fashion_blog.exceptions.CommentNotFoundException;
import com.example.fashion_blog.exceptions.PostNotFoundException;
import com.example.fashion_blog.exceptions.ResourceAlreadyExistException;
import com.example.fashion_blog.exceptions.ResourceNotFoundException;
import com.example.fashion_blog.models.Admin;
import com.example.fashion_blog.models.Categories;
import com.example.fashion_blog.models.Comments;
import com.example.fashion_blog.models.Customer;
import com.example.fashion_blog.models.Likes;
import com.example.fashion_blog.models.Post;
import com.example.fashion_blog.repositories.CategoryRepository;
import com.example.fashion_blog.repositories.CommentsRepository;
import com.example.fashion_blog.repositories.CustomerRepository;
import com.example.fashion_blog.repositories.LikesRepository;
import com.example.fashion_blog.repositories.PostRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private CommentsRepository commentsRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @MockBean
    private LikesRepository likesRepository;

    @MockBean
    private PostRepository postRepository;



    @Test
    void testCreateUser2() {
        Customer customer = new Customer();
        customer.setComments(new ArrayList<>());
        customer.setCreatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        customer.setCustomerId(123L);
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setLikes(new ArrayList<>());
        customer.setPassword("iloveyou");
        when(customerRepository.save((Customer) any())).thenReturn(customer);
        when(customerRepository.findByEmail((String) any())).thenReturn(Optional.empty());

        CustomerSignUpDto customerSignUpDto = new CustomerSignUpDto();
        customerSignUpDto.setEmail("jane.doe@example.org");
        customerSignUpDto.setFirstName("Jane");
        customerSignUpDto.setLastName("Doe");
        customerSignUpDto.setPassword("iloveyou");
        customerServiceImpl.createUser(customerSignUpDto);
        verify(customerRepository).save((Customer) any());
        verify(customerRepository).findByEmail((String) any());
    }


    @Test
    void testLikePost2() {
        Customer customer = new Customer();
        customer.setComments(new ArrayList<>());
        customer.setCreatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        customer.setCustomerId(123L);
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setLikes(new ArrayList<>());
        customer.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Long) any())).thenReturn(ofResult);

        Admin admin = new Admin();
        admin.setAdminId(123L);
        admin.setCreatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        admin.setEmail("jane.doe@example.org");
        admin.setFirstName("Jane");
        admin.setLastName("Doe");
        admin.setPassword("iloveyou");
        admin.setPosts(new ArrayList<>());

        Categories categories = new Categories();
        categories.setCategoryId(123L);
        categories.setCategoryName("Category Name");
        categories.setPost(new ArrayList<>());

        Post post = new Post();
        post.setAdmin(admin);
        post.setCategory(categories);
        post.setComments(new ArrayList<>());
        post.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setDateModified(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setDescription("The characteristics of someone or something");
        post.setImageUrl("https://example.org/example");
        post.setLikes(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        Optional<Post> ofResult1 = Optional.of(post);
        when(postRepository.findById((Long) any())).thenReturn(ofResult1);
        when(likesRepository.save((Likes) any())).thenThrow(new ResourceAlreadyExistException("An error occurred"));
        assertThrows(ResourceAlreadyExistException.class, () -> customerServiceImpl.likePost(123L, 123L));
        verify(customerRepository).findById((Long) any());
        verify(postRepository).findById((Long) any());
        verify(likesRepository).save((Likes) any());
    }




    @Test
    void testLogin() {
        Customer customer = new Customer();
        customer.setComments(new ArrayList<>());
        customer.setCreatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        customer.setCustomerId(123L);
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setLikes(new ArrayList<>());
        customer.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findByEmailAndPassword((String) any(), (String) any())).thenReturn(ofResult);

        CustomerLoginDto customerLoginDto = new CustomerLoginDto();
        customerLoginDto.setEmail("jane.doe@example.org");
        customerLoginDto.setPassword("iloveyou");
        assertSame(customer, customerServiceImpl.login(customerLoginDto));
        verify(customerRepository).findByEmailAndPassword((String) any(), (String) any());
    }


    @Test
    void testPostComment2() {
        Customer customer = new Customer();
        customer.setComments(new ArrayList<>());
        customer.setCreatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        customer.setCustomerId(123L);
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setLikes(new ArrayList<>());
        customer.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Long) any())).thenReturn(ofResult);

        Admin admin = new Admin();
        admin.setAdminId(123L);
        admin.setCreatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        admin.setEmail("jane.doe@example.org");
        admin.setFirstName("Jane");
        admin.setLastName("Doe");
        admin.setPassword("iloveyou");
        admin.setPosts(new ArrayList<>());

        Categories categories = new Categories();
        categories.setCategoryId(123L);
        categories.setCategoryName("Category Name");
        categories.setPost(new ArrayList<>());

        Post post = new Post();
        post.setAdmin(admin);
        post.setCategory(categories);
        post.setComments(new ArrayList<>());
        post.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setDateModified(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setDescription("The characteristics of someone or something");
        post.setImageUrl("https://example.org/example");
        post.setLikes(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        Optional<Post> ofResult1 = Optional.of(post);
        when(postRepository.findById((Long) any())).thenReturn(ofResult1);
        when(commentsRepository.save((Comments) any())).thenThrow(new ResourceAlreadyExistException("An error occurred"));

        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setComment("Comment");
        assertThrows(ResourceAlreadyExistException.class, () -> customerServiceImpl.postComment(123L, 123L, commentsDto));
        verify(customerRepository).findById((Long) any());
        verify(postRepository).findById((Long) any());
        verify(commentsRepository).save((Comments) any());
    }


    @Test
    void testShowPostByCategory() {
        assertNull(customerServiceImpl.showPostByCategory(123L));
    }


    @Test
    void testViewAllComments2() {
        Customer customer = new Customer();
        customer.setComments(new ArrayList<>());
        customer.setCreatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        customer.setCustomerId(123L);
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setLikes(new ArrayList<>());
        customer.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Long) any())).thenReturn(ofResult);
        when(postRepository.findById((Long) any())).thenThrow(new ResourceAlreadyExistException("An error occurred"));
        assertThrows(ResourceAlreadyExistException.class, () -> customerServiceImpl.viewAllComments(123L, 123L));
        verify(customerRepository).findById((Long) any());
        verify(postRepository).findById((Long) any());
    }



    @Test
    void testViewAllPosts() {
        Customer customer = new Customer();
        customer.setComments(new ArrayList<>());
        customer.setCreatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        customer.setCustomerId(123L);
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setLikes(new ArrayList<>());
        customer.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Long) any())).thenReturn(ofResult);
        ArrayList<Post> postList = new ArrayList<>();
        when(postRepository.findAll()).thenReturn(postList);
        List<Post> actualViewAllPostsResult = customerServiceImpl.viewAllPosts(123L);
        assertSame(postList, actualViewAllPostsResult);
        assertTrue(actualViewAllPostsResult.isEmpty());
        verify(customerRepository).findById((Long) any());
        verify(postRepository).findAll();
    }



    @Test
    void testViewPost2() {
        Customer customer = new Customer();
        customer.setComments(new ArrayList<>());
        customer.setCreatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        customer.setCustomerId(123L);
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setLikes(new ArrayList<>());
        customer.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Long) any())).thenReturn(ofResult);
        when(postRepository.findById((Long) any())).thenThrow(new ResourceAlreadyExistException("An error occurred"));
        assertThrows(ResourceAlreadyExistException.class, () -> customerServiceImpl.viewPost(123L, 123L));
        verify(customerRepository).findById((Long) any());
        verify(postRepository).findById((Long) any());
    }
}

