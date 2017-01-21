package com.projectx.cwm.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

/**
 * Created by sl0 on 11/16/16.
 */
public class UserLoginDetails {
    private String username;
    private String password;

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    private Collection<GrantedAuthority> authorities;

    public UserLoginDetails(User principal) {
        username = principal.getUsername();
        password = principal.getPassword();
        authorities = principal.getAuthorities();
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
