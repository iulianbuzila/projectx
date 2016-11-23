package com.projectx.cwm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by sl0 on 11/23/16.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExists extends RuntimeException {

    public ResourceAlreadyExists(String resourceName) {
        super("Resource already exists " + resourceName + ".");
    }
}
