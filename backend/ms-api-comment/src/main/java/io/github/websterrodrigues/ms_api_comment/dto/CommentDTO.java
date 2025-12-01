package io.github.websterrodrigues.ms_api_comment.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

public record CommentDTO(

        @NotBlank(message = "Campo obrigatório!")
        String message,
        LocalDate creationDate,

        @NotBlank(message = "Campo obrigatório!")
        UUID idAuthor,

        UUID idParentCommentId,

        @NotBlank(message = "Campo obrigatório!")
        UUID idComplaint) {
}
