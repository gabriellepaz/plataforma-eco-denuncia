package io.github.websterrodrigues.ms_api_complaint.dto;

import io.github.websterrodrigues.ms_api_complaint.model.Attachment;
import io.github.websterrodrigues.ms_api_complaint.model.enums.OperationStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ComplaintDTO(

        @NotBlank(message = "Campo obrigatório!")
        String title,

        @NotBlank(message = "Campo obrigatório!")
        String message,

        LocalDate creationDate,

        List<Attachment> attachments,

        OperationStatus operationStatus,

        @NotBlank(message = "Campo obrigatório!")
        UUID idAuthor,

        UUID idAssociatedCompany) {
}
