package com.projectx.cwm.domain;

import com.projectx.cwm.services.UserService;
import org.apache.log4j.Logger;
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

    @ManyToOne(optional = false)
    Role role;

    private String function;

    public UserGroup(User user, Group group, Role role, String function) {
        this.user = user;
        this.group = group;
        this.role = role;
        this.function = function;
    }

    public UserGroup() {
    }

    public Role getRole() {

        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
