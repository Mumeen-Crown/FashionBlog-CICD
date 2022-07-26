package com.example.fashion_blog.repositories;

import com.example.fashion_blog.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    List<Comments> findCommentsByPost(Long postId);
}
