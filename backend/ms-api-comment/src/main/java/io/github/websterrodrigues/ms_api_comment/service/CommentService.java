package io.github.websterrodrigues.ms_api_comment.service;

import io.github.websterrodrigues.ms_api_comment.exception.EntityNotFoundException;
import io.github.websterrodrigues.ms_api_comment.model.Comment;
import io.github.websterrodrigues.ms_api_comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository repository;


    public Comment save(Comment comment){
        return repository.save(comment);
    }

    public Comment findById(UUID id){
        Optional<Comment> obj =  repository.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException(String.format("Comentário não encontrada! ID: %s", id)));
    }

    public void delete(UUID id){
        Comment comment = findById(id);
        repository.delete(comment);
    }
}
