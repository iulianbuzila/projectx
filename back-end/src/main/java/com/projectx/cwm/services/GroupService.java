package com.projectx.cwm.services;

import com.projectx.cwm.domain.Group;
import com.projectx.cwm.domain.Log;
import com.projectx.cwm.domain.User;
import com.projectx.cwm.domain.UserGroup;
import com.projectx.cwm.exceptions.ResourceAlreadyExists;
import com.projectx.cwm.exceptions.ResourceNotFound;
import com.projectx.cwm.models.GroupModel;
import com.projectx.cwm.models.UserLoginDetails;
import com.projectx.cwm.models.UserModel;
import com.projectx.cwm.repositories.GroupRepository;
import com.projectx.cwm.repositories.LogRepository;
import com.projectx.cwm.repositories.UserGroupRepository;
import com.projectx.cwm.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sl0 on 1/17/17.
 */
@Service
public class GroupService {
    final UserGroupRepository userGroupRepository;
    final GroupRepository groupRepository;
    final UserRepository userRepository;
    final LogRepository logRepository;
    private final Logger logger = Logger.getLogger(GroupService.class);

    @Autowired
    public GroupService(UserGroupRepository userGroupRepository, GroupRepository groupRepository, UserRepository userRepository, LogRepository logRepository) {
        this.userGroupRepository = userGroupRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.logRepository = logRepository;
    }

    public void addGroup(GroupModel groupModel, UserLoginDetails loggedUser) {
        Group group = groupRepository.findByName(groupModel.getName());
        if (group != null) {
            throw new ResourceAlreadyExists("Group with name " + groupModel.getName() + " already exists.");
        }
        group = new Group();
        if (groupModel.getAdminId() != null) {
            User admin = userRepository.findOne(groupModel.getAdminId());
            group.setAdmin(admin);
        }
        User creator = userRepository.findByUsername(loggedUser.getUsername());
        group.setCreator(creator);
        group.setName(groupModel.getName());

        groupRepository.save(group);
        groupRepository.flush();
        logger.info("Created group with name " + groupModel.getName() + ".");
        logRepository.save(new Log("User " + loggedUser.getUsername() + " created group " + group.getName(),
                creator));
    }

    public List<GroupModel> getAll(UserLoginDetails loggedUser) {
        logger.info("User " + loggedUser.getUsername() + " got all groups.");
        List<Group> groups = groupRepository.findAll();
        List<GroupModel> groupModels = new ArrayList<>();
        for (Group g :
                groups) {
            List<UserModel> userModels = userRepository.findByGroup(g.getId()).stream().map(UserModel::new).collect(Collectors.toList());

            GroupModel groupModel = new GroupModel(g, userModels);
            groupModels.add(groupModel);
        }
        return groupModels;
    }

    public void deleteGroup(Long groupId, UserLoginDetails loggedUser) {
        Group group = groupRepository.findOne(groupId);
        if (group == null){
            throw new ResourceNotFound("Group " + groupId + " not found.");
        }
        groupRepository.delete(group);
        groupRepository.flush();
        logRepository.save(new Log("User " + loggedUser.getUsername() + " deleted group " + group.getName(),
                userRepository.findByUsername(loggedUser.getUsername())));
    }

    public void addUserToGroup(UserModel userModel, Long groupId, UserLoginDetails loggedUser) {
        User user = userRepository.findOne(userModel.getId());
        if (user == null) {
            throw new ResourceNotFound("User " + userModel.getId() + " not found.");
        }

        Group group = groupRepository.findOne(groupId);
        if (group == null) {
            throw new ResourceNotFound("Group " + groupId + " not found.");
        }

        logRepository.save(new Log("User " + loggedUser.getUsername() + " added " + user.getUsername() + " to" +
                " group " + group.getName() + ".",
                userRepository.findByUsername(loggedUser.getUsername())));

        if (userModel.getRoleInGroup() == null) {
            throw new RuntimeException("Role in group not specified!");
        }

        UserGroup userGroup = new UserGroup();
        userGroup.setFunction(userModel.getRoleInGroup());
        userGroup.setGroup(group);
        userGroup.setUser(user);
        userGroupRepository.save(userGroup);
        userGroupRepository.flush();
    }

    public void editGroup(Long groupId, GroupModel groupModel, UserLoginDetails loggedUser) {
        Group group = groupRepository.findOne(groupId);
        if (group == null){
            throw new ResourceNotFound("Group " + groupId + " not found.");
        }
        group.setName(groupModel.getName());
        if (groupModel.getAdminId() != null) {
            User admin = userRepository.findOne(groupModel.getAdminId());
            group.setAdmin(admin);
        }
        groupRepository.save(group);
        groupRepository.flush();
    }

    public void removeUserFromGroup(Long groupId, Long userId, UserLoginDetails loggedUser) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new ResourceNotFound("User " + userId + " not found.");
        }

        Group group = groupRepository.findOne(groupId);
        if (group == null) {
            throw new ResourceNotFound("Group " + groupId + " not found.");
        }
        logRepository.save(new Log("User " + loggedUser.getUsername() + " removed " + user.getUsername() + " from" +
                " group " + group.getName() + ".", userRepository.findByUsername(loggedUser.getUsername())));
        UserGroup ug = userGroupRepository.findByUserAndGroup(user, group);
        userGroupRepository.delete(ug);
    }

    public GroupModel getGroup(Long groupId, UserLoginDetails loggedUser) {
        Group group = groupRepository.findOne(groupId);
        if (group == null) {
            throw new ResourceNotFound("Group " + groupId + " not found.");
        }
        List<UserModel> userModels = userRepository.findByGroup(group.getId()).stream().map(UserModel::new).collect(Collectors.toList());

        GroupModel groupModel = new GroupModel(group, userModels);
        return groupModel;
    }
}
