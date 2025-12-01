package io.github.websterrodrigues.ms_api_complaint.service;

import io.github.websterrodrigues.ms_api_complaint.exception.EntityNotFoundException;
import io.github.websterrodrigues.ms_api_complaint.model.Attachment;
import io.github.websterrodrigues.ms_api_complaint.model.Complaint;
import io.github.websterrodrigues.ms_api_complaint.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository repository;

    @Autowired
    private ComplaintService complaintService;

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

    public void saveFile(UUID id, MultipartFile file) throws IOException {
        Complaint complaint = complaintService.findById(id);

        Attachment attachment = new Attachment();
        attachment.setComplaint(complaint);

        attachment.setFileName(file.getOriginalFilename());
        attachment.setExtension(extractExtension(file.getOriginalFilename()));
        attachment.setSizeInMb(file.getSize() / (1024.0 * 1024.0));
        attachment.setFileContent(file.getBytes());

        complaint.getAttachments().add(attachment);

        complaintService.save(complaint);

    }

    public void saveFileList(UUID id, List<MultipartFile> files) throws IOException {
        Complaint complaint = complaintService.findById(id);

        for (MultipartFile file : files) {

            Attachment attachment = new Attachment();
            attachment.setComplaint(complaint);

            attachment.setFileName(file.getOriginalFilename());
            attachment.setExtension(extractExtension(file.getOriginalFilename()));
            attachment.setSizeInMb(file.getSize() / (1024.0 * 1024.0));
            attachment.setFileContent(file.getBytes());

            complaint.getAttachments().add(attachment);
        }

        complaintService.save(complaint);
    }


    private String extractExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) return "";
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
    }

}
