package io.github.websterrodrigues.ms_api_complaint.controller;

import io.github.websterrodrigues.ms_api_complaint.dto.CloseComplaintDTO;
import io.github.websterrodrigues.ms_api_complaint.dto.ComplaintDTO;
import io.github.websterrodrigues.ms_api_complaint.dto.mapper.ComplaintMapper;
import io.github.websterrodrigues.ms_api_complaint.model.Complaint;
import io.github.websterrodrigues.ms_api_complaint.service.ComplaintService;
import io.github.websterrodrigues.ms_api_complaint.utils.GenericController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("api-complaints/complaints")
public class ComplaintController implements GenericController {

    @Autowired
    private ComplaintService service;

    @Autowired
    private ComplaintMapper mapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid ComplaintDTO complaintDTO){

        Complaint complaint = mapper.toEntity(complaintDTO);
        service.save(complaint);
        URI location = generateHeaderLocation(complaint.getId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> closeComplaint(@PathVariable String id){

        Complaint complaint = service.findById(UUID.fromString(id));
        service.closedComplaint(complaint);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplaintDTO> findById(@PathVariable String id){
        ComplaintDTO dto = mapper.toDto(service.findById(UUID.fromString(id)));
        return ResponseEntity.ok(dto);
    }


}
