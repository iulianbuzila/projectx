package com.projectx.cwm.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by sl0 on 11/22/16.
 */
@Entity(name = "group_")
public class Group extends AbstractPersistable<Long> {

    @OneToMany(mappedBy = "group")
    private Set<UserGroup> userGroup;
    @Column(length = 100)
    private String description;
    @Column(length = 100)
    private String groupName;

    public Group() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserGroup> getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Set<UserGroup> userGroup) {
        this.userGroup = userGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
