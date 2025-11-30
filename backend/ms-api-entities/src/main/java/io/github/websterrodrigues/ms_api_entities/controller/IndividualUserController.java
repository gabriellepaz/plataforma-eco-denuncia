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
@RequestMapping("/users")
public class IndividualUserController implements GenericController {

    private final IndividualUserService service;
    private final IndividualUserMapper mapper;

    @Autowired
    private RoleService roleService;

    public IndividualUserController(IndividualUserMapper mapper, IndividualUserService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid IndividualUserDTO userDTO){

        IndividualUser user = mapper.toEntity(userDTO);

        Set<Role> roles = userDTO.roles().stream()
                .map(roleService::findByName)
                .collect(Collectors.toSet());
        user.setRoles(roles);

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
