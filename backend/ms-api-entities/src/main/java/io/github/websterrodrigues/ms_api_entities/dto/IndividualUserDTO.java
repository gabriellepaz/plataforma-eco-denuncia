package io.github.websterrodrigues.ms_api_entities.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record IndividualUserDTO(

    @NotBlank(message = "Campo obrigatório!")
    @Size(min = 11, max = 14, message = "Campo não corresponde os padrões de tamanho!")
    String document,

    @NotBlank(message = "Campo obrigatório!")
    @Email
    String email,

    @NotBlank(message = "Campo obrigatório!")
    String password,
    Integer age,
    String sex,

    @NotEmpty(message = "Declare ao menos uma role!")
    List<@NotBlank(message = "Verifique se não há campos vazios!") String> roles) {

}
