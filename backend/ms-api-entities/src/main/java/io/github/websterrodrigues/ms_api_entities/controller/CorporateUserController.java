package io.github.websterrodrigues.ms_api_entities.controller;

import io.github.websterrodrigues.ms_api_entities.dto.CorporateUserDTO;
import io.github.websterrodrigues.ms_api_entities.dto.mappers.CorporateUserMapper;
import io.github.websterrodrigues.ms_api_entities.model.CorporateUser;
import io.github.websterrodrigues.ms_api_entities.service.CorporateUserService;
import io.github.websterrodrigues.ms_api_entities.utils.GenericController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("api-entities/corporate-corporateUsers")
public class CorporateUserController implements GenericController {

    @Autowired
    private CorporateUserService service;

    @Autowired
    private CorporateUserMapper mapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid CorporateUserDTO corporateUserDTO){

        CorporateUser corporateUser = mapper.toEntity(corporateUserDTO);
        service.save(corporateUser);
        URI location = generateHeaderLocation(corporateUser.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody @Valid CorporateUserDTO corporateUserDTO){

        UUID idUser = UUID.fromString(id);
        CorporateUser corporateUser = mapper.toEntity(corporateUserDTO);
        corporateUser.setId(idUser); //Garantir que o ID seja definido corretamente NÃO REMOVER
        service.update(corporateUser);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable String id){

        service.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorporateUserDTO> findById(@PathVariable String id){
        CorporateUserDTO dto = mapper.toDto(service.findById(UUID.fromString(id)));
        return ResponseEntity.ok(dto);
    }
}
