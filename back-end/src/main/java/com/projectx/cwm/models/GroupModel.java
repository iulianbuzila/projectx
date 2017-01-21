package com.projectx.cwm.models;

import com.projectx.cwm.domain.Group;

import java.util.List;

/**
 * Created by sl0 on 1/17/17.
 */
public class GroupModel {
    private Long id;
    private String name;
    private Long creatorId;
    private Long adminId;
    private List<UserModel> users;

    public GroupModel() {
    }

    public GroupModel(Group group) {
        id = group.getId();
        name = group.getName();

        creatorId = group.getCreator().getId();
        adminId = group.getAdmin().getId();
    }

    public GroupModel(Group group, List<UserModel> users) {
        id = group.getId();
        name = group.getName();
        creatorId = group.getCreator().getId();
        if (group.getAdmin() != null) {
            adminId = group.getAdmin().getId();
        }
        this.users = users;
    }

    public GroupModel(Long id, String name, Long creatorId, Long adminID, List<UserModel> users) {
        this.id = id;
        this.name = name;
        this.creatorId = creatorId;
        this.adminId = adminID;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
