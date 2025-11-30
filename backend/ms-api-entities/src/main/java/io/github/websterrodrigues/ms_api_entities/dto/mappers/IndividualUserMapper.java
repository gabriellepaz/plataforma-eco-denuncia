package io.github.websterrodrigues.ms_api_entities.dto.mappers;

import io.github.websterrodrigues.ms_api_entities.dto.IndividualUserDTO;
import io.github.websterrodrigues.ms_api_entities.model.IndividualUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IndividualUserMapper {

    IndividualUser toEntity(IndividualUserDTO dto);
    IndividualUserDTO toDto(IndividualUser user);
}
