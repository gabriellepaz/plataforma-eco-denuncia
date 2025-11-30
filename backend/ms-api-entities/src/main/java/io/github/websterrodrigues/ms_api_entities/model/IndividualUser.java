package io.github.websterrodrigues.ms_api_entities.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "individual_users")
public class IndividualUser extends BaseUser {

    @Column(name = "idade")
    private Integer age;

    @Column(name = "sexo")
    private String sex;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "individual_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public IndividualUser(UUID id, String document, String email, String password, Integer age, String sex) {
        super(id, document, email, password);
        this.age = age;
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
