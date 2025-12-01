package io.github.websterrodrigues.ms_api_complaint.controller;

import io.github.websterrodrigues.ms_api_complaint.service.AttachmentService;
import io.github.websterrodrigues.ms_api_complaint.utils.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api-complaints/attachment")
public class AttachmentController implements GenericController {

    @Autowired
    private AttachmentService service;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(String id){
        service.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }








}
