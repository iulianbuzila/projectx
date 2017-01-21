package com.projectx.cwm.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by sl0 on 11/22/16.
 */
@Entity(name = "group_")
public class Group extends AbstractPersistable<Long> {

    @Column(length = 100)
    private String name;

    @ManyToOne(optional = false)
    private User creator;
    @ManyToOne()
    private User admin;

    public Group(String name, User creator, User admin) {

        this.name = name;
        this.creator = creator;
        this.admin = admin;
    }

    public Group() {
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String groupName) {
        this.name = groupName;
    }
}
