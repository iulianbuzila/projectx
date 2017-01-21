package com.projectx.cwm.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by sl0 on 1/17/17.
 */
@Entity
public class Workflow extends AbstractPersistable<Long> {
    LocalDateTime dateOfCreation;
    @OneToMany(mappedBy = "workflow")
    Set<Log> logs;
    @OneToMany(mappedBy = "workflow")
    Set<Document> documents;
    private String name;
    private String fundingType;
    @ManyToOne()
    private User user;
    public Workflow(LocalDateTime dateOfCreation, String name, String fundingType, User user, Set<Log> logs) {
        this.dateOfCreation = dateOfCreation;
        this.name = name;
        this.fundingType = fundingType;
        this.user = user;
        this.logs = logs;
    }
    public Workflow() {

    }

    public Workflow(String name, String fundingType, LocalDateTime dateOfCreation) {

        this.name = name;
        this.fundingType = fundingType;
        this.dateOfCreation = dateOfCreation;
    }

    public Workflow(String name, String fundingType, User user, LocalDateTime dateOfCreation) {

        this.name = name;
        this.fundingType = fundingType;
        this.user = user;
        this.dateOfCreation = dateOfCreation;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFundingType() {
        return fundingType;
    }

    public void setFundingType(String fundingType) {
        this.fundingType = fundingType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
