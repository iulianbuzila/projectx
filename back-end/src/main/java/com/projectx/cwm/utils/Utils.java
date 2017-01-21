package com.projectx.cwm.utils;

import com.projectx.cwm.models.UserLoginDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * Created by sl0 on 1/17/17.
 */
public class Utils {
    public static UserLoginDetails getUserDetails() {
        return new UserLoginDetails((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
