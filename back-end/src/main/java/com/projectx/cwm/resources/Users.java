package com.projectx.cwm.resources;

import com.projectx.cwm.models.UserLoginDetails;
import com.projectx.cwm.models.UserModel;
import com.projectx.cwm.services.UserService;
import com.projectx.cwm.utils.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by sl0 on 10/31/16.
 */
@RestController
@RequestMapping("/api/users/")
public class Users {
    private final UserService userService;
    private final Logger logger = Logger.getLogger(Users.class);

    @Autowired
    public Users(UserService userService) {
        this.userService = userService;
    }


    


//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    ResponseEntity<?> edit(@RequestBody UserModel userModel, @PathVariable Long userId) {

        UserLoginDetails loggedUser = Utils.getUserDetails();

        logger.info("Editing user '" + userModel + "'.");
        userModel = userService.edit(userModel, userId, loggedUser);
        logger.info("Successfully edited user '" + userModel + "'.");

        return new ResponseEntity<>(userModel, new HttpHeaders(), HttpStatus.OK);
    }



    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<?> getUsers() {
        UserLoginDetails loggedUser = Utils.getUserDetails();
//        UserLoginDetails loggedUser = null;

        logger.info("Getting all users.");
        Set<UserModel> userModels = userService.getUsers(loggedUser);
        logger.info("Successfully got all users.");
        return new ResponseEntity<>(userModels, HttpStatus.OK);

    }

//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody UserModel userModel) {
        UserLoginDetails loggedUser = Utils.getUserDetails();
//        UserLoginDetails loggedUser = null;

        logger.info("Adding user '" + userModel + "'.");
        UserModel user = userService.add(userModel, loggedUser);
        logger.info("Successfully added user '" + user + "'.");

        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable Long userId) {

        UserLoginDetails loggedUser = Utils.getUserDetails();
//        UserLoginDetails loggedUser = (UserLoginDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        logger.info("Deleting user '" + userId + "'.");

        userService.delete(userId, loggedUser);

        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'CONTRIBUTOR', 'READER')")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    ResponseEntity<?> readUser(@PathVariable Long userId) {
        logger.info("Getting user '" + userId + "'.");
        UserLoginDetails loggedUser = Utils.getUserDetails();
        UserModel um = this.userService.getUser(userId, loggedUser);

        return new ResponseEntity<>(um, new HttpHeaders() ,HttpStatus.OK);
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'CONTRIBUTOR', 'READER')")
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    ResponseEntity<?> forgotPassword(@RequestBody UserModel userModel) {
        logger.info("Forgot password '" + userModel.getEmail() + "'.");


        userService.forgotPassword(userModel.getEmail());

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
