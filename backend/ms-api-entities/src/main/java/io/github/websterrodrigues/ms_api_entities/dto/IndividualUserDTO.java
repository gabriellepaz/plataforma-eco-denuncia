package io.github.websterrodrigues.ms_api_entities.dto;

import java.util.List;

public record IndividualUserDTO(


    String document,
    String email,
    String password,
    Integer age,
    String sex,
    List<String> roles) {

}
