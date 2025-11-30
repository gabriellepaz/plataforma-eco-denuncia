package io.github.websterrodrigues.ms_api_complaint.service;

import io.github.websterrodrigues.ms_api_complaint.exception.EntityNotFoundException;
import io.github.websterrodrigues.ms_api_complaint.model.Attachment;
import io.github.websterrodrigues.ms_api_complaint.model.Complaint;
import io.github.websterrodrigues.ms_api_complaint.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository repository;

    public Attachment save(Attachment attachment){
        return repository.save(attachment);
    }

    public Attachment findById(UUID id){
        Optional<Attachment> obj =  repository.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException(String.format("Anexo não encontrado! ID: %s", id)));
    }

    public Attachment update(Attachment attachment){
        return repository.save(attachment);
    }

    public void delete(UUID id){
        Attachment attachment = findById(id);
        repository.delete(attachment);
    }
}
