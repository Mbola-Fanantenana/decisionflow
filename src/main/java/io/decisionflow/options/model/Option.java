package io.decisionflow.options.model;

import io.decisionflow.decisions.model.Decision;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.ManyToOne;

public class Option extends PanacheEntity {

    public String label;

    @ManyToOne
    public Decision decision;
}
