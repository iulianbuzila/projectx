package com.projectx.cwm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by iulia on 1/21/2017.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserNotAllowed extends RuntimeException {
    public UserNotAllowed(Long userId, Long groupId) {
        super("User '" + userId + "' not allowed to edit group '" + groupId + "'.");
    }

    public UserNotAllowed(String s) {
        super(s);
    }
}
