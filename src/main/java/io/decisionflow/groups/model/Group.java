package io.decisionflow.groups.model;

import io.decisionflow.users.model.User;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class Group extends PanacheEntity {

    public String name;

    @ManyToMany
    @JoinTable(name = "group_members")
    public Set<User> members;
}
