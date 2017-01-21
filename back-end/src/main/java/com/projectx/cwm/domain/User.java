package com.projectx.cwm.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by sl0 on 11/2/16.
 */
@Entity
public class User extends AbstractPersistable<Long> {

    private String username;
    private String password;
    private String email;
    private String firstName;
    //    private List<GrantedAuthority> authorities;
    private String lastName;
    @OneToMany(mappedBy = "user")
    private Set<Workflow> workflows;
    @OneToMany(mappedBy = "user")
    private Set<Log> logs;
    @OneToMany(mappedBy = "creator")
    private Set<Group> createdGroups;
    @OneToMany(mappedBy = "admin")
    private Set<Group> adminGroups;

    public User(String username, String password, String email, String firstName, String lastName, Set<Log> logs) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.logs = logs;
    }
    public User(String username, String password, String email, String firstName, String lastName/*, Set<UserGroup> userGroup*/) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
//        this.userGroup = userGroup;
    }

    public User() {
    }

    public Set<Workflow> getWorkflows() {
        return workflows;
    }

    public void setWorkflows(Set<Workflow> workflows) {
        this.workflows = workflows;
    }

    public Set<Group> getCreatedGroups() {
        return createdGroups;
    }

    public void setCreatedGroups(Set<Group> createdGroups) {
        this.createdGroups = createdGroups;
    }
//    @OneToMany(mappedBy = "user")
//    private Set<UserGroup> userGroup;

    public Set<Group> getAdminGroups() {
        return adminGroups;
    }

    public void setAdminGroups(Set<Group> adminGroups) {
        this.adminGroups = adminGroups;
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }

//    public Set<UserGroup> getUserGroup() {
//        return userGroup;
//    }

//    public void setUserGroup(Set<UserGroup> userGroup) {
//        this.userGroup = userGroup;
//    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }
}
