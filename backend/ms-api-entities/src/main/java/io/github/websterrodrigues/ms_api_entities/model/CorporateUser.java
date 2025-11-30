package io.github.websterrodrigues.ms_api_entities.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "corporate_users")
public class CorporateUser extends BaseUser {

    @Column(name = "esta_autenticado")
    private Boolean isAuthenticated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "corporate_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", isAuthenticated=" + isAuthenticated + '}';
    }
}
