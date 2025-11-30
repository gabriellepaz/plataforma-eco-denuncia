package io.github.websterrodrigues.ms_api_entities.repository;

import io.github.websterrodrigues.ms_api_entities.model.CorporateUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CorporateUserRepository extends JpaRepository<CorporateUser, UUID> {
}
