package io.github.websterrodrigues.ms_api_comment.repository;

import io.github.websterrodrigues.ms_api_comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
