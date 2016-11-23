package com.projectx.cwm.resources;

import com.projectx.cwm.models.UserLoginDetails;
import com.projectx.cwm.models.UserModel;
import com.projectx.cwm.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sl0 on 10/31/16.
 */
@RestController
@RequestMapping("/api/users/")
public class Users {
    private final UserService userService;
    Logger logger = Logger.getLogger(Users.class);

    @Autowired
    public Users(UserService userService) {
        this.userService = userService;
    }

    // TODO: 11/2/16 de separat callurile la repo in alt modul
    // TODO: 11/3/16 configurat loggerul
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody UserModel userModel) {

        UserLoginDetails loggedUser = (UserLoginDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        logger.info("Adding user '" + userModel + "'.");
        UserModel user = userService.add(userModel);
        logger.info("Successfully added user '" + user + "'.");

        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable Long userId) {
        UserLoginDetails loggedUser = (UserLoginDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        logger.info("Deleting user '" + userId + "'.");

        userService.delete(userId, loggedUser);
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'CONTRIBUTOR', 'READER')")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    UserModel readUser(@PathVariable Long userId) {
        return this.userService.getUser(userId);
    }
}
