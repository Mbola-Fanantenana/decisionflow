package io.decisionflow.decisions.model;

import io.decisionflow.groups.model.Group;
import io.decisionflow.options.Option;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public class Decision extends PanacheEntity {

    public String title;

    @ManyToOne
    public Group group;

    @OneToMany(mappedBy = "decision")
    public List<Option> options;

    public boolean closed;
}
