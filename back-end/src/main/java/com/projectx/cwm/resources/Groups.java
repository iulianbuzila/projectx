package com.projectx.cwm.resources;

import com.projectx.cwm.models.GroupModel;
import com.projectx.cwm.models.Response;
import com.projectx.cwm.models.UserLoginDetails;
import com.projectx.cwm.models.UserModel;
import com.projectx.cwm.services.GroupService;
import com.projectx.cwm.utils.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sl0 on 1/17/17.
 */
@RestController
@RequestMapping("/api/groups/")
public class Groups {
    private final Logger logger = Logger.getLogger(Groups.class);

    @Autowired
    GroupService groupService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getGroups() {
        UserLoginDetails loggedUser = Utils.getUserDetails();
        logger.info("getGroups");
        List<GroupModel> groups = groupService.getAll(loggedUser);
        return new ResponseEntity<>(groups, new HttpHeaders(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addGroup(@RequestBody GroupModel groupModel) {

//		return new ResponseEntity<>(groupService.addGroup(group),HttpStatus.OK);
        UserLoginDetails loggedUser = Utils.getUserDetails();
        groupService.addGroup(groupModel, loggedUser);
        return new ResponseEntity<>(new Response(true), HttpStatus.CREATED);

    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/{groupId}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> deleteGroup(@PathVariable("groupId") Long groupId) {
        UserLoginDetails loggedUser = Utils.getUserDetails();
        groupService.deleteGroup(groupId, loggedUser);
        return new ResponseEntity<>(new Response(true), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/{groupId}", method = RequestMethod.POST)
    public ResponseEntity<Response> addUserToGroup(@PathVariable("groupId") Long groupId,
                                                   @RequestBody UserModel userModel) {
        UserLoginDetails loggedUser = Utils.getUserDetails();
        groupService.addUserToGroup(userModel, groupId, loggedUser);
        return new ResponseEntity<>(new Response(true), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/{groupId}", method = RequestMethod.PUT)
    public ResponseEntity<Response> editGroup(@PathVariable("groupId") Long groupId,
                                              @RequestBody GroupModel groupModel) {
        UserLoginDetails loggedUser = Utils.getUserDetails();
        groupService.editGroup(groupId, groupModel, loggedUser);
        return new ResponseEntity<>(new Response(true), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/{groupId}/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> deleteUserFromGroup(@PathVariable("userId") Long userId,
                                                        @PathVariable("groupId") Long groupId) {
        UserLoginDetails loggedUser = Utils.getUserDetails();
        groupService.removeUserFromGroup(groupId, userId, loggedUser);
        return new ResponseEntity<>(new Response(true), HttpStatus.OK);

    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
    public ResponseEntity<?> getGroup(@PathVariable("groupId") Long groupId) {
        UserLoginDetails loggedUser = Utils.getUserDetails();
        GroupModel groupModel = groupService.getGroup(groupId, loggedUser);
        return new ResponseEntity<>(groupModel, HttpStatus.OK);

    }
}
