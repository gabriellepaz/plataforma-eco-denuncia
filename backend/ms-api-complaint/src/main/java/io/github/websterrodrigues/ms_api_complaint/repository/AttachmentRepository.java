package io.github.websterrodrigues.ms_api_complaint.repository;

import io.github.websterrodrigues.ms_api_complaint.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
