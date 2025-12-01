package io.github.websterrodrigues.ms_api_comment.repository;

import io.github.websterrodrigues.ms_api_comment.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment,UUID> {
}
