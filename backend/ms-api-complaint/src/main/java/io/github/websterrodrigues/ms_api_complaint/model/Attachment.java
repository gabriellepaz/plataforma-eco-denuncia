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

    @Lob
    @Column(name = "conteudo_arquivo")
    private byte[] fileContent;

    @Column(name = "nome_arquivo")
    private String fileName;

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

    public Attachment(UUID id, String fileName, Double sizeInMb, LocalDateTime attachmentDate,
                      LocalDateTime dateAttachmentUpdatedate, Complaint complaint) {
        this.id = id;
        this.fileName = fileName;
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

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
