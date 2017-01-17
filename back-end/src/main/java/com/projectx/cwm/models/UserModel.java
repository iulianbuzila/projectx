package com.projectx.cwm.models;

import com.projectx.cwm.domain.User;

/**
 * Created by sl0 on 11/16/16.
 */
public class UserModel {
    private Long id;

    public UserModel(Long id, String username, String role, String password, String firstName, String lastName, String email) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private String username;
    private String role;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public UserModel(String username, String password, String role, String email) {
        this.username = username;
        this.role = role;
        this.password = password;
        this.email = email;
    }

    public UserModel(User user) {
        id = user.getId();
        username = user.getUsername();
        firstName = user.getFirstName();
        lastName = user.getLastName();
//        role = user.getRole();
        email = user.getEmail();
    }

    public UserModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
                ", email='" + email + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
