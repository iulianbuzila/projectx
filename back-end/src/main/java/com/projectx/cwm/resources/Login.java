package com.projectx.cwm.resources;

import com.projectx.cwm.domain.User;
import com.projectx.cwm.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by sl0 on 11/17/16.
 */
@RestController
@RequestMapping("/api/login")
public class Login {
    private final UserService userService;
    Logger logger = Logger.getLogger(Login.class);

    @Autowired
    public Login(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody User user) {
        logger.info("Logging in '" + user + "'.");
        user = userService.add(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().build().toUri());
        logger.info("Successfully logged in user '" + user + "'.");
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);

    }
}
