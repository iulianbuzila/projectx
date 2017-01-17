package com.projectx.cwm.models;

import com.projectx.cwm.domain.User;

/**
 * Created by sl0 on 11/16/16.
 */
public class UserLoginDetails {
    private String username;
    private String password;

    public UserLoginDetails(User user) {
        username = user.getUsername();
        password = user.getPassword();
    }

    public UserLoginDetails() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
