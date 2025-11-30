package io.github.websterrodrigues.ms_api_entities.service;

import io.github.websterrodrigues.ms_api_entities.exception.EntityNotFoundException;
import io.github.websterrodrigues.ms_api_entities.model.IndividualUser;
import io.github.websterrodrigues.ms_api_entities.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class IndividualUserService {

    private final UserRepository repository;

    public IndividualUserService(UserRepository repository) {
        this.repository = repository;
    }

    public IndividualUser save(IndividualUser user){
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
}
