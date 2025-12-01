package io.github.websterrodrigues.ms_api_comment.controller;

import io.github.websterrodrigues.ms_api_comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api-comments/comments")
public class CommentController {

    @Autowired
    private CommentService service;
}
