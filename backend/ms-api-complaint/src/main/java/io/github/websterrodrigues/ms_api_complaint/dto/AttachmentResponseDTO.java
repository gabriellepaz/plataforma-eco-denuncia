package io.github.websterrodrigues.ms_api_complaint.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AttachmentResponseDTO(
        UUID id,
        String fileName,
        String extension,
        Double sizeInMb,
        LocalDateTime attachmentDate,
        LocalDateTime lastUpdateDate
) {}
