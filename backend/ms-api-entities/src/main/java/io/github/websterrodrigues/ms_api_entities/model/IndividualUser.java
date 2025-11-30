package io.github.websterrodrigues.ms_api_entities.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_individual_users")
public class IndividualUser extends BaseUser {

    @Column(name = "idade")
    private Integer age;

    @Column(name = "sexo")
    private String sex;

    public IndividualUser(UUID id, String document, String email, String name, String password, Integer age, String sex) {
        super(id, document, email, name, password);
        this.age = age;
        this.sex = sex;
    }

    public IndividualUser() {

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


    @Override
    public String toString() {
        return super.toString() +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
