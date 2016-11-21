package com.projectx.cwm.resources;

import com.projectx.cwm.domain.User;
import com.projectx.cwm.models.UserModel;
import com.projectx.cwm.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody User user) {
        logger.info("Adding user '" + user + "'.");
        user = userService.add(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri());
        logger.info("Successfully added user '" + user + "'.");
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    UserModel readUser(@PathVariable Long userId) {
        return this.userService.getUser(userId);
    }
}
