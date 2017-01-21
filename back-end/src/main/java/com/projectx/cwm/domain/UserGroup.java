package com.projectx.cwm.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by sl0 on 11/22/16.
 */
@Entity
public class UserGroup extends AbstractPersistable<Long> {

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Group group;

    private String function;

    public UserGroup(User user, Group group, String function) {
        this.user = user;
        this.group = group;
        this.function = function;
    }

    public UserGroup() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
