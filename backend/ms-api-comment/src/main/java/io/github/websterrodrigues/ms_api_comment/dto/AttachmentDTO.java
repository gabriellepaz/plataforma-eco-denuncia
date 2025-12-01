package io.github.websterrodrigues.ms_api_comment.dto;

import org.springframework.web.multipart.MultipartFile;

public record AttachmentDTO(
        MultipartFile file
) {
}