package com.projectx.cwm.models;

import com.projectx.cwm.domain.User;

/**
 * Created by sl0 on 11/16/16.
 */
public class UserModel {
    private Long id;
    private String username;
    private String role;
    private String password;
    public UserModel(String username, String password, String role) {
        this.username = username;
        this.role = role;
        this.password = password;
    }
    public UserModel(User user) {
        id = user.getId();
        username = user.getUsername();
        role = user.getRole();
    }

    public UserModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}