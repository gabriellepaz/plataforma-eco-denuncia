package io.github.websterrodrigues.ms_api_comment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "mensagem")
    private String message;

    @Column(name= "data_criacao")
    private LocalDate creationDate;

    @Column(name = "id_autor")
    private UUID idAuthor;

    @Column(name = "id_comentario_pai")
    private UUID idParentCommentId;

    @Column(name = "id_denuncia")
    private UUID idComplaint;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Attachment> attachments = new ArrayList<>();

    public Comment(){

    }

    public Comment(UUID id, String message, LocalDate creationDate, UUID idAuthor, UUID idParentCommentId, UUID idComplaint) {
        this.id = id;
        this.message = message;
        this.creationDate = creationDate;
        this.idAuthor = idAuthor;
        this.idParentCommentId = idParentCommentId;
        this.idComplaint = idComplaint;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public UUID getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(UUID idAuthor) {
        this.idAuthor = idAuthor;
    }

    public UUID getIdParentCommentId() {
        return idParentCommentId;
    }

    public void setIdParentCommentId(UUID idParentCommentId) {
        this.idParentCommentId = idParentCommentId;
    }

    public UUID getIdComplaint() {
        return idComplaint;
    }

    public void setIdComplaint(UUID idComplaint) {
        this.idComplaint = idComplaint;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
