package com.semestrwork.demo.Repository;

import com.semestrwork.demo.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
