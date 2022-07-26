package com.example.fashion_blog.repositories;

import com.example.fashion_blog.models.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AdminRepositoryTest {

    @Autowired
    private AdminRepository underTest;


    @Test
    void findByEmail() {
        // given
        Admin admin = new Admin();
        admin.setFirstName("Bee");
        admin.setLastName("Honey");
        admin.setEmail("bee@gmail.com");
        admin.setPassword("12345");
        underTest.save(admin);
        //when
        Optional<Admin> dbAdmin = underTest.findByEmail("bee@gmail.com");
        //then
        if (dbAdmin.isPresent()) {
            assertThat(dbAdmin.get()).isEqualTo(admin);
        }
        else{
            assertThat(dbAdmin.get()).isEqualTo(null);
        }

    }

    @Test
    void findByEmailAndPassword() {
        //given
        Admin admin = new Admin();
        admin.setFirstName("Bee");
        admin.setLastName("Honey");
        admin.setEmail("bee@gmail.com");
        admin.setPassword("12345");
        underTest.save(admin);
        //when
        Optional<Admin> dbAdmin = underTest.findByEmailAndPassword("bee@gmail.com", "12345");
        //then
        if (dbAdmin.isPresent()){
            assertThat(dbAdmin.get()).isEqualTo(admin);
        }else{
            assertThat(dbAdmin).isEqualTo(null);
        }
    }
}