package io.github.websterrodrigues.ms_api_entities.controller;

import io.github.websterrodrigues.ms_api_entities.dto.IndividualUserDTO;
import io.github.websterrodrigues.ms_api_entities.dto.mappers.IndividualUserMapper;
import io.github.websterrodrigues.ms_api_entities.model.IndividualUser;
import io.github.websterrodrigues.ms_api_entities.model.Role;
import io.github.websterrodrigues.ms_api_entities.service.IndividualUserService;
import io.github.websterrodrigues.ms_api_entities.service.RoleService;
import io.github.websterrodrigues.ms_api_entities.utils.GenericController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api-entities/users")
public class IndividualUserController implements GenericController {

    @Autowired
    private IndividualUserService service;

    @Autowired
    private IndividualUserMapper mapper;



    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid IndividualUserDTO userDTO){

        IndividualUser user = mapper.toEntity(userDTO);
        service.save(user);
        URI location = generateHeaderLocation(user.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody @Valid IndividualUserDTO userDTO){

        UUID idUser = UUID.fromString(id);
        IndividualUser user = mapper.toEntity(userDTO);
        user.setId(idUser); //Garantir que o ID seja definido corretamente NÃO REMOVER
        service.update(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable String id){

        service.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndividualUserDTO> findById(@PathVariable String id){
        IndividualUserDTO dto = mapper.toDto(service.findById(UUID.fromString(id)));
        return ResponseEntity.ok(dto);
    }


}
