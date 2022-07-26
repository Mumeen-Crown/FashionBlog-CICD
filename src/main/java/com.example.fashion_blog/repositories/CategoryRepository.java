package com.example.fashion_blog.repositories;

import com.example.fashion_blog.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {

    Categories findByCategoryName(String categoryName);
}
