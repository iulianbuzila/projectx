package com.projectx.cwm.models;

/**
 * Created by sl0 on 1/16/17.
 */
public class Response {
    boolean success;

    public Response() {
    }

    public Response(boolean success) {

        this.success = success;
    }

    public boolean isSuccess() {

        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
