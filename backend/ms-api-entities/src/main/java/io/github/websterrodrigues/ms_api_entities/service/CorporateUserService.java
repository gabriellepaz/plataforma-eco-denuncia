package io.github.websterrodrigues.ms_api_entities.service;

import io.github.websterrodrigues.ms_api_entities.exception.EntityNotFoundException;
import io.github.websterrodrigues.ms_api_entities.model.CorporateUser;
import io.github.websterrodrigues.ms_api_entities.model.Role;
import io.github.websterrodrigues.ms_api_entities.repository.CorporateUserRepository;
import io.github.websterrodrigues.ms_api_entities.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CorporateUserService {

    @Autowired
    private CorporateUserRepository repository;

    @Autowired
    private RoleRepository roleService;

    public CorporateUser save(CorporateUser corporateUser){
        mapRoles(corporateUser);
        return repository.save(corporateUser);
    }

    public CorporateUser findById(UUID id){
        Optional<CorporateUser> obj =  repository.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException(String.format("Usuário não encontrado! ID: %s", id)));
    }

    public void update(CorporateUser corporateUser){
        findById(corporateUser.getId());
        repository.save(corporateUser);
    }

    public void delete(UUID id){
        CorporateUser corporateUser = findById(id);
        repository.delete(corporateUser);
    }

//Metodo desativado devido dependência da api e-mail
//    public void changeAuthenticatedStatus(UUID id){
//        if(req){
//        CorporateUser corporateUser = findById(id);
//        corporateUser.setAuthenticated(true);
//        repository.save(corporateUser);
//        }
//    }

    private void mapRoles(CorporateUser corporateUser){
        Set<Role> roles = corporateUser.getRoles().stream()
                .map(role -> roleService.findByName(role.getName()))
                .collect(Collectors.toSet());
        corporateUser.setRoles(roles);
    }

}
