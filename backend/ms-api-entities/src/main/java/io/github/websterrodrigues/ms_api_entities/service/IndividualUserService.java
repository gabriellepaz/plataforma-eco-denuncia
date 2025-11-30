package io.github.websterrodrigues.ms_api_entities.service;

import io.github.websterrodrigues.ms_api_entities.exception.EntityNotFoundException;
import io.github.websterrodrigues.ms_api_entities.model.IndividualUser;
import io.github.websterrodrigues.ms_api_entities.model.Role;
import io.github.websterrodrigues.ms_api_entities.repository.RoleRepository;
import io.github.websterrodrigues.ms_api_entities.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IndividualUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleService;


    public IndividualUser save(IndividualUser user){
        mapRoles(user);
        return repository.save(user);
    }

    public IndividualUser findById(UUID id){
        Optional<IndividualUser> obj =  repository.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException(String.format("User não encontrado! ID: %s", id)));
    }

    public void update(IndividualUser user){
        findById(user.getId());
        repository.save(user);
    }

    public void delete(UUID id){
       IndividualUser user = findById(id);
        repository.delete(user);
    }

    private void mapRoles(IndividualUser user){
        Set<Role> roles = user.getRoles().stream()
                .map(role -> roleService.findByName(role.getName()))
                .collect(Collectors.toSet());
        user.setRoles(roles);
    }

}
