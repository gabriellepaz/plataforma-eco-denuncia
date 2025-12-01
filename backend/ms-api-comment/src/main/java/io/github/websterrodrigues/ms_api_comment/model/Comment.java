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

    @Column(name = "id_denuncia")
    private UUID idComplaint;

    @OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Attachment> attachments = new ArrayList<>();






}
