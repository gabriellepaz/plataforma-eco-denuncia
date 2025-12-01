package io.github.websterrodrigues.ms_api_comment.controller;

import io.github.websterrodrigues.ms_api_comment.service.AttachmentService;
import io.github.websterrodrigues.ms_api_comment.utils.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api-comments/attachment")
public class AttachmentController implements GenericController {

    @Autowired
    private AttachmentService service;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}