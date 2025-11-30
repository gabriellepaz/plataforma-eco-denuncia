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

    public Role save(Role role){
        return repository.save(role);
    }

    public Role findById(Long id){
        Optional<Role> obj =  repository.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException(String.format("User não encontrado! ID: %s", id)));
    }

    public void update(Role role){
        findById(role.getId());
        repository.save(role);
    }

    public void delete(Long id){
        Role role = findById(id);
        repository.delete(role);
    }
    
    public Role findByName(String name){
        return repository.findByName(name);
    }
}
