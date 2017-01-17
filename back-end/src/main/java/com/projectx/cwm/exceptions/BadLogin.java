package com.projectx.cwm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by sl0 on 1/16/17.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class BadLogin extends RuntimeException {
    public BadLogin(String message) {
        super(message);
    }
}
