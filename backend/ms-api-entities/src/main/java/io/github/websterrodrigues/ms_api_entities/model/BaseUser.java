package io.github.websterrodrigues.ms_api_entities.model;


import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public class BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "documento", nullable = false)
    private String document;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String password;

    public BaseUser(){

    }

    public BaseUser(UUID id, String document, String email, String password) {
        this.id = id;
        this.document = document;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseUser baseUser = (BaseUser) o;
        return Objects.equals(id, baseUser.id) && Objects.equals(document, baseUser.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, document);
    }

    @Override
    public String toString() {
        return "BaseUser{" +
                "document='" + document + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'';
    }
}
