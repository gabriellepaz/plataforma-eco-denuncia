package io.github.websterrodrigues.ms_api_entities.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record CorporateUserDTO(

        @NotBlank(message = "Campo obrigatório!")
        @Size(min = 11, max = 14, message = "Campo não corresponde os padrões de tamanho!")
        String documento,

        @NotBlank(message = "Campo obrigatório!")
        @Email
        String email,

        @NotBlank(message = "Campo obrigatório!")
        String name,

        @NotBlank(message = "Campo obrigatório!")
        String password,

        @NotEmpty(message = "Declare ao menos uma role!")
        Set<@NotBlank(message = "Verifique se não há campos vazios!") String> roles) {
}
