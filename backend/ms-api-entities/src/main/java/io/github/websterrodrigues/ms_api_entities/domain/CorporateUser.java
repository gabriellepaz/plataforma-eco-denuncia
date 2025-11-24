package io.github.websterrodrigues.ms_api_entities.domain;

import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class CorporateUser extends BaseUser {

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
        return "CorporateUser{" +
                "isAuthenticated=" + isAuthenticated +
                '}';
    }
}
