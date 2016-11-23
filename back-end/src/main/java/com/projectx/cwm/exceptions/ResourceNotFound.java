package com.projectx.cwm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by sl0 on 11/17/16.
 */
@ResponseStatus(HttpStatus.GONE)
public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String resourceName) {
        super("Resource not found " + resourceName + ".");
    }
}
