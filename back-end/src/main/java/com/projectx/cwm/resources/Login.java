package com.projectx.cwm.resources;

import com.projectx.cwm.models.UserModel;
import com.projectx.cwm.services.LoginService;
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
    private final LoginService loginService;
    Logger logger = Logger.getLogger(Login.class);

    @Autowired
    public Login(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody UserModel userModel) {
        logger.info("Logging in '" + userModel + "'.");

        userModel = loginService.logIn(userModel);

        logger.info("Successfully logged in user '" + userModel + "'.");
        return new ResponseEntity<>(userModel, new HttpHeaders(), HttpStatus.ACCEPTED);

    }
}
