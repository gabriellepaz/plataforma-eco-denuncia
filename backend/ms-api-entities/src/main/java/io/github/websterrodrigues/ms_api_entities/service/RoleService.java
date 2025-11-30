package io.github.websterrodrigues.ms_api_entities.service;

import io.github.websterrodrigues.ms_api_entities.exception.EntityNotFoundException;
import io.github.websterrodrigues.ms_api_entities.model.Role;
import io.github.websterrodrigues.ms_api_entities.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository repository;

    public Role findByName(String name){
        return repository.findByName(name);
    }
}
