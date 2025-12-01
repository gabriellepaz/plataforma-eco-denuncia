package io.github.websterrodrigues.ms_api_comment.controller;

import io.github.websterrodrigues.ms_api_comment.dto.CommentDTO;
import io.github.websterrodrigues.ms_api_comment.dto.mapper.CommentMapper;
import io.github.websterrodrigues.ms_api_comment.model.Comment;
import io.github.websterrodrigues.ms_api_comment.service.AttachmentService;
import io.github.websterrodrigues.ms_api_comment.service.CommentService;
import io.github.websterrodrigues.ms_api_comment.utils.GenericController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api-comments/comments")
public class CommentController implements GenericController {

    @Autowired
    private CommentService service;
    
    @Autowired
    private CommentMapper mapper;

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid CommentDTO commentDTO){

        Comment comment = mapper.toEntity(commentDTO);
        service.save(comment);
        URI location = generateHeaderLocation(comment.getId());
        return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> findById(@PathVariable String id){
        CommentDTO dto = mapper.toDto(service.findById(UUID.fromString(id)));
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{id}/attachment")
    public ResponseEntity<Void> saveFile(@PathVariable("id") String commentId, @RequestParam("file") MultipartFile file) throws IOException {

        attachmentService.saveFile(UUID.fromString(commentId), file);
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/{id}/attachment/list")
    public ResponseEntity<Void> saveFileList(@PathVariable("id") String commentId, @RequestParam("files") List<MultipartFile> files) throws IOException {

        attachmentService.saveFileList(UUID.fromString(commentId), files);
        return ResponseEntity.noContent().build();

    }
}
