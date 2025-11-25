package io.github.websterrodrigues.ms_api_entities.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
public class IndividualUser extends BaseUser {

    private Integer age;
    private String sex;

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

    @Override
    public String toString() {
        return "IndividualUser{" +
                "id=" + getId() +
                ", email='" + getEmail() + '\'' +
                ", document='" + getDocument() + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
