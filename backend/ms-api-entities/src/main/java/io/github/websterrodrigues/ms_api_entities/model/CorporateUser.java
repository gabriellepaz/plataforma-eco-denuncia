package io.github.websterrodrigues.ms_api_entities.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "tb_corporate_users")
public class CorporateUser extends BaseUser {

    @Column(name = "esta_autenticado")
    private Boolean isAuthenticated;

    public CorporateUser(UUID id, String document, String email, String password, Boolean isAuthenticated) {
        super(id, document, email, password);
        this.isAuthenticated = isAuthenticated;
    }

    public Boolean getAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", isAuthenticated=" + isAuthenticated + '}';
    }
}
