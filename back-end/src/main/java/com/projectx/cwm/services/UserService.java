package com.projectx.cwm.services;

import com.projectx.cwm.domain.User;
import com.projectx.cwm.exceptions.ResourceAlreadyExists;
import com.projectx.cwm.exceptions.ResourceNotFound;
import com.projectx.cwm.exceptions.UserNotFoundException;
import com.projectx.cwm.models.UserLoginDetails;
import com.projectx.cwm.models.UserModel;
import com.projectx.cwm.repositories.UserRepository;
import com.projectx.cwm.resources.Users;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by sl0 on 11/16/16.
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserModel add(UserModel userModel) {
        User user = userRepository.findByUsername(userModel.getUsername());
        if (user != null){
            throw new ResourceAlreadyExists(userModel.getUsername());
        }
        User newUser = new User();
        newUser.setUsername(userModel.getUsername());
        newUser.setPassword(userModel.getPassword());
        newUser.setRole(userModel.getRole());
        newUser = userRepository.save(newUser);
        userRepository.flush();
        return new UserModel(newUser);
    }

    public boolean delete(Long userId, UserLoginDetails loggedUser){
        User user = userRepository.findOne(userId);
        if (user == null){
            throw new ResourceNotFound("user " + userId.toString());
        }
        userRepository.delete(user);
        userRepository.flush();
        return true;
    }

    public UserModel getUser(Long userId) {
        User user = userRepository.findOne(userId);
        if (user == null){
            throw new UserNotFoundException(userId.toString());
        }
        return new UserModel(user);
    }

    public UserModel edit(UserModel userModel, Long userId, UserModel loggedUser){
        User user = userRepository.findOne(userId);
        if (user == null){
            throw new UserNotFoundException(userId.toString());
        }
        user.setUsername(userModel.getUsername());
        user.setPassword(userModel.getPassword());
        user.setRole(userModel.getRole());
        userRepository.save(user);
        userRepository.flush();
        return new UserModel(user);
    }

//    public UserModel logIn(UserModel userModel) {
//        return null;
//    }
}
