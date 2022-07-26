package com.example.fashion_blog.service.serviceImpl;

import com.example.fashion_blog.dtos.admindto.AdminSignUpDto;
import com.example.fashion_blog.models.Admin;
import com.example.fashion_blog.repositories.AdminRepository;
import com.example.fashion_blog.repositories.CategoryRepository;
import com.example.fashion_blog.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    @Mock
    private AdminRepository adminRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private CategoryRepository categoryRepository;

    private AdminServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest =new AdminServiceImpl(adminRepository, postRepository, categoryRepository);
    }

    @Test
    void createAdmin() {
        //given
        AdminSignUpDto admin = new AdminSignUpDto();
        admin.setFirstName("Bee");
        admin.setLastName("Honey");
        admin.setEmail("bee@gmail.com");
        admin.setPassword("12345");
        //when
        underTest.createAdmin(admin);
        //then
        ArgumentCaptor<Admin> adminArgumentCaptor
                = ArgumentCaptor.forClass(Admin.class);
        verify(adminRepository).save(adminArgumentCaptor.capture());
        Admin capturedAdmin = adminArgumentCaptor.getValue();

    }

    @Test
    @Disabled
    void login() {
    }

    @Test
    @Disabled
    void createCategory() {
    }

    @Test
    @Disabled
    void uploadPost() {
    }

    @Test
    @Disabled
    void fetchAllPosts() {
    }

    @Test
    @Disabled
    void fetchPost() {
    }

    @Test
    @Disabled
    void modifyPost() {
    }

    @Test
    @Disabled
    void deletePost() {
    }

    @Test
    @Disabled
    void viewNumberOfLikes() {
    }

    @Test
    @Disabled
    void viewComments() {
    }
}