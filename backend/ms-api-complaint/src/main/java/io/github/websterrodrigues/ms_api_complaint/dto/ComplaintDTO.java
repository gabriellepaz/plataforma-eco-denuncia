package io.github.websterrodrigues.ms_api_complaint.dto;

import io.github.websterrodrigues.ms_api_complaint.model.enums.OperationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ComplaintDTO(

        @NotBlank(message = "Campo obrigatório!")
        String title,

        @NotBlank(message = "Campo obrigatório!")
        String message,

        LocalDate creationDate,

        OperationStatus operationStatus,

        @NotNull(message = "Campo obrigatório!")
        UUID idAuthor,

        UUID idAssociatedCompany) {
}
