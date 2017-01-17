package com.projectx.cwm.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by sl0 on 1/16/17.
 */
@Entity
public class Role extends AbstractPersistable<Long> {
    String role;

    @ManyToOne(optional = false)
    User user;

    @OneToMany(mappedBy = "role")
    Set<UserGroup> userGroup;

    public Role(String role, User user, Set<UserGroup> userGroup) {
        this.role = role;
        this.user = user;
        this.userGroup = userGroup;
    }

    public Role(String role, User user) {
        this.role = role;
        this.user = user;
    }

    public Role() {
    }

    public Role(String role) {

        this.role = role;
    }

    public Set<UserGroup> getUserGroup() {

        return userGroup;
    }

    public void setUserGroup(Set<UserGroup> userGroup) {
        this.userGroup = userGroup;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
