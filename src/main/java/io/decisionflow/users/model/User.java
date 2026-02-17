package io.decisionflow.users.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User extends PanacheEntity {

    @NotBlank
    public String name;
}
