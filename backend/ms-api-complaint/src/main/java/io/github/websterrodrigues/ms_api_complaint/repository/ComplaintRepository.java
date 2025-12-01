package io.github.websterrodrigues.ms_api_complaint.repository;

import io.github.websterrodrigues.ms_api_complaint.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComplaintRepository extends JpaRepository<Complaint, UUID> {
}
