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
    User user;

    @ManyToOne(optional = false)
    Group group;

    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
