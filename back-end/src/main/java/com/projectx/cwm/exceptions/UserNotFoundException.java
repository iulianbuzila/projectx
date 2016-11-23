package com.projectx.cwm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by sl0 on 11/2/16.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userId) {
        super("Could not find user '" + userId + "'.");
    }
}
