package io.github.websterrodrigues.ms_api_entities.repository;

import io.github.websterrodrigues.ms_api_entities.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
