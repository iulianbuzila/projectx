package com.projectx.cwm.models;

import com.projectx.cwm.domain.User;

/**
 * Created by sl0 on 11/16/16.
 */
public class UserModel {
    private String username;
    private String role;

    public UserModel(User user) {
        username = user.getUsername();
        role = user.getRole();

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
