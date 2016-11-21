package com.projectx.cwm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

/**
 * Created by sl0 on 11/2/16.
 */
@Entity
public class User extends AbstractPersistable<Long> {

    public String username;
    @JsonIgnore
    public String password;

    public User(String name, String password) {
        this.username = name;
        this.password = password;
    }

    User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + "', " +
                "username='" + username + '\'' +
                '}';
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
