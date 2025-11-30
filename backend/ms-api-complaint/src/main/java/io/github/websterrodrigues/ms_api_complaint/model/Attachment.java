package io.github.websterrodrigues.ms_api_complaint.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tb_attachment")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "arquivo")
    private String file;

    @Column(name = "nome_arquivo")
    private String extension;

    @Column(name = "tamanho_mb", nullable = false)
    private Double sizeInMb;

    @CreatedDate
    @Column(name = "data_anexo", updatable = false)
    private LocalDateTime attachmentDate;

    @LastModifiedDate
    @Column(name = "data_atualizacao_anexo")
    private LocalDateTime dateAttachmentUpdatedate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;

    public Attachment() {

    }

    public Attachment(UUID id, String file, String extension, Double sizeInMb, LocalDateTime attachmentDate, LocalDateTime dateAttachmentUpdatedate, Complaint complaint) {
        this.id = id;
        this.file = file;
        this.extension = extension;
        this.sizeInMb = sizeInMb;
        this.attachmentDate = attachmentDate;
        this.dateAttachmentUpdatedate = dateAttachmentUpdatedate;
        this.complaint = complaint;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Double getSizeInMb() {
        return sizeInMb;
    }

    public void setSizeInMb(Double sizeInMb) {
        this.sizeInMb = sizeInMb;
    }

    public LocalDateTime getAttachmentDate() {
        return attachmentDate;
    }

    public void setAttachmentDate(LocalDateTime attachmentDate) {
        this.attachmentDate = attachmentDate;
    }

    public LocalDateTime getDateAttachmentUpdatedate() {
        return dateAttachmentUpdatedate;
    }

    public void setDateAttachmentUpdatedate(LocalDateTime dateAttachmentUpdatedate) {
        this.dateAttachmentUpdatedate = dateAttachmentUpdatedate;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }
}
