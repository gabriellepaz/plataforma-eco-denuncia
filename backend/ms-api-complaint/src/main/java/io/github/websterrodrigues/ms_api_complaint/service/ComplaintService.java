package io.github.websterrodrigues.ms_api_complaint.service;

import io.github.websterrodrigues.ms_api_complaint.exception.EntityNotFoundException;
import io.github.websterrodrigues.ms_api_complaint.model.Attachment;
import io.github.websterrodrigues.ms_api_complaint.model.Complaint;
import io.github.websterrodrigues.ms_api_complaint.model.enums.OperationStatus;
import io.github.websterrodrigues.ms_api_complaint.repository.AttachmentRepository;
import io.github.websterrodrigues.ms_api_complaint.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository repository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    public Complaint save(Complaint complaint){
        return repository.save(complaint);
    }

    public Complaint findById(UUID id){
        Optional<Complaint> obj =  repository.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException(String.format("Denuncia não encontrada! ID: %s", id)));
    }

    public void replaceAttachments(Complaint complaint, List<Attachment> newAttachments) {

        if (complaint.isOperacionalStatusClosed()) {
            throw new IllegalArgumentException("Não é possível atualizar anexos de uma denúncia fechada.");
        }

        complaint.getAttachments().clear();

        newAttachments.forEach(a -> {
            a.setComplaint(complaint);
            complaint.getAttachments().add(a);
        });

        repository.save(complaint);
    }

    public void updateAttachment(Complaint complaint) {

        if (complaint.isOperacionalStatusClosed()) {
            throw new IllegalArgumentException("Não é possível atualizar anexos de uma denúncia fechada.");
        }

        complaint.getAttachments()
                .forEach(attachmentRepository::save);
    }


    public void delete(UUID id){
        Complaint complaint = findById(id);
        repository.delete(complaint);
    }

    public void closedComplaint(Complaint complaint){
        complaint.setOperationStatus(OperationStatus.CLOSED);
        repository.save(complaint);
    }



}
