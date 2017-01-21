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
    private String role;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public Role(String role, Set<User> user) {
        this.role = role;
        this.users = user;
    }

    public Role() {
    }

    public Role(String role) {

        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> user) {
        this.users = user;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
