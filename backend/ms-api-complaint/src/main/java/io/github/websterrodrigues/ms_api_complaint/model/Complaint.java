package io.github.websterrodrigues.ms_api_complaint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.websterrodrigues.ms_api_complaint.model.enums.OperationStatus;
import io.github.websterrodrigues.ms_api_complaint.model.enums.SolutionStatus;
import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "titulo", nullable = false, length = 150)
    private String title;

    @Column(name = "mensagem", nullable = false, length = 2000)
    private String message;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate creationDate;

    @OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Attachment> attachments;

    @Enumerated(EnumType.STRING)
    private SolutionStatus solutionStatus;

    @Enumerated(EnumType.STRING)
    private OperationStatus operationStatus;

    @Column(name = "id_author", nullable = false)
    private UUID idAuthor;

    @Column(name = "id_associated_company")
    private UUID idAssociatedCompany;

    @LastModifiedDate
    private LocalDateTime dateUpdateOperationStatus;

    @LastModifiedDate
    private LocalDateTime dateUpdateSolutionStatus;

    public Complaint(){

    }

    public Complaint(UUID id, String title, String message, LocalDate creationDate,
                     SolutionStatus solutionStatus, OperationStatus operationStatus, UUID idAuthor, UUID idAssociatedCompany,
                     LocalDateTime dateUpdateOperationStatus, LocalDateTime dateUpdateSolutionStatus) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.creationDate = creationDate;
        this.solutionStatus = solutionStatus;
        this.operationStatus = operationStatus;
        this.idAuthor = idAuthor;
        this.idAssociatedCompany = idAssociatedCompany;
        this.dateUpdateOperationStatus = dateUpdateOperationStatus;
        this.dateUpdateSolutionStatus = dateUpdateSolutionStatus;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public SolutionStatus getSolutionStatus() {
        return solutionStatus;
    }

    public void setSolutionStatus(SolutionStatus solutionStatus) {
        this.solutionStatus = solutionStatus;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

    public UUID getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(UUID idAuthor) {
        this.idAuthor = idAuthor;
    }

    public UUID getIdAssociatedCompany() {
        return idAssociatedCompany;
    }

    public void setIdAssociatedCompany(UUID idAssociatedCompany) {
        this.idAssociatedCompany = idAssociatedCompany;
    }

    public LocalDateTime getDateUpdateOperationStatus() {
        return dateUpdateOperationStatus;
    }

    public void setDateUpdateOperationStatus(LocalDateTime dateUpdateOperationStatus) {
        this.dateUpdateOperationStatus = dateUpdateOperationStatus;
    }

    public LocalDateTime getDateUpdateSolutionStatus() {
        return dateUpdateSolutionStatus;
    }

    public void setDateUpdateSolutionStatus(LocalDateTime dateUpdateSolutionStatus) {
        this.dateUpdateSolutionStatus = dateUpdateSolutionStatus;
    }

    public boolean isAttachmentTotalSizeExceeded() {
        double totalSizeInMb = 0.0;
        if (attachments != null) {
            for (Attachment attachment : attachments) {
                totalSizeInMb += attachment.getSizeInMb();
            }
        }
        return totalSizeInMb > 10.0;
    }

    public boolean isOperacionalStatusClosed() {
        return this.operationStatus == OperationStatus.CLOSED;
    }

    public boolean isAttachmentLimitExceeded() {
        return attachments != null && attachments.size() > 10;
    }
    

    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", creationDate=" + creationDate +
                ", attachments=" + attachments +
                ", solutionStatus=" + solutionStatus +
                ", operationStatus=" + operationStatus +
                ", idAuthor=" + idAuthor +
                ", idAssociatedCompany=" + idAssociatedCompany +
                ", dateUpdateOperationStatus=" + dateUpdateOperationStatus +
                ", dateUpdateSolutionStatus=" + dateUpdateSolutionStatus +
                '}';
    }
}
