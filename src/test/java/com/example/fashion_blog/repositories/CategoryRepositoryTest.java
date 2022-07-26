package com.example.fashion_blog.repositories;

import com.example.fashion_blog.models.Categories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void findByCategoryName() {
        //given
        Categories category = new Categories();
        category.setCategoryName("Men's Trouser");
        categoryRepository.save(category);
        //when
        Categories dbCategory = categoryRepository.findByCategoryName("Men's trouser");
        //then
        if (Objects.nonNull(dbCategory)) {
            assertThat(dbCategory).isEqualTo(category);
        }
        else {
            assertThat(dbCategory).isEqualTo(null);
        }
    }
}