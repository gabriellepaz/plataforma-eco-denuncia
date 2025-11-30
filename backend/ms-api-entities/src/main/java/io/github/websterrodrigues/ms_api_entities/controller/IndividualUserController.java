package io.github.websterrodrigues.ms_api_entities.controller;

import io.github.websterrodrigues.ms_api_entities.dto.IndividualUserDTO;
import io.github.websterrodrigues.ms_api_entities.model.IndividualUser;
import io.github.websterrodrigues.ms_api_entities.service.IndividualUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class IndividualUserController {

    private final IndividualUserService service;

    public IndividualUserController(IndividualUserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody IndividualUserDTO userDTO){

    }


}
