package io.github.websterrodrigues.ms_api_entities.dto.mappers;

import io.github.websterrodrigues.ms_api_entities.dto.IndividualUserDTO;
import io.github.websterrodrigues.ms_api_entities.model.IndividualUser;
import io.github.websterrodrigues.ms_api_entities.model.Role;
import io.github.websterrodrigues.ms_api_entities.repository.RoleRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class IndividualUserMapper {

    @Autowired
    private RoleRepository roleRepository;

    public abstract IndividualUser toEntity(IndividualUserDTO dto);
    public abstract   IndividualUserDTO toDto(IndividualUser user);

    protected Role mapRole(String roleName) {
        return roleRepository.findByName(roleName);
    }

    protected String mapRole(Role role) {
        return role.getName();
    }
}
