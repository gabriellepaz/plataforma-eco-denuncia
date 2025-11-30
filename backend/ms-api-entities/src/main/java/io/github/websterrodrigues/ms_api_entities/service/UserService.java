package io.github.websterrodrigues.ms_api_entities.service;

import io.github.websterrodrigues.ms_api_entities.model.IndividualUser;
import io.github.websterrodrigues.ms_api_entities.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public IndividualUser save(IndividualUser user){
        return repository.save(user);
    }

    public void update(IndividualUser user){
        repository.findById(user.getId());
        repository.save(user);
    }

    public void delete(UUID id){
        Optional<IndividualUser> user =  repository.findById(id);
        repository.delete(user.get());
    }
}
