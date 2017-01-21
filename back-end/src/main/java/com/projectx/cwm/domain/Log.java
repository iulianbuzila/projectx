package com.projectx.cwm.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by sl0 on 1/17/17.
 */
@Entity
public class Log extends AbstractPersistable<Long> {

    //    private Long uuid;
    private String message;

    @ManyToOne()
    private Workflow workflow;

    @ManyToOne()
    private User user;

    public Log(String message) {
        this.message = message;
    }

    public Log(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public Log() {

    }

    public Log(Workflow workflow, String message, User user) {

        this.workflow = workflow;
        this.message = message;
        this.user = user;
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
