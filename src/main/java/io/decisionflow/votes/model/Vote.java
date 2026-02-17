package io.decisionflow.votes.model;

import io.decisionflow.options.model.Option;
import io.decisionflow.users.model.User;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.ManyToOne;

public class Vote extends PanacheEntity {

    @ManyToOne
    public User user;

    @ManyToOne
    public Option option;
}
