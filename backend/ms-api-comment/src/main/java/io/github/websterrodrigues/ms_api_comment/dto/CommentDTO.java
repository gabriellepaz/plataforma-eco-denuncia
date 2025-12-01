package io.github.websterrodrigues.ms_api_comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CommentDTO(

        @NotBlank(message = "Campo obrigatório!")
        String message,
        LocalDate creationDate,

        @NotNull(message = "Campo obrigatório!")
        UUID idAuthor,

        UUID idParentCommentId,

        @NotNull(message = "Campo obrigatório!")
        UUID idComplaint) {
}
