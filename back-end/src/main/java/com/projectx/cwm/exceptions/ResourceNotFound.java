package com.projectx.cwm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by sl0 on 11/17/16.
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class ResourceNotFound extends Throwable {
}
