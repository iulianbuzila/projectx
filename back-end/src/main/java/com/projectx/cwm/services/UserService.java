package com.projectx.cwm.services;

import com.projectx.cwm.domain.User;
import com.projectx.cwm.exceptions.UserNotFoundException;
import com.projectx.cwm.models.UserModel;
import com.projectx.cwm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

/**
 * Created by sl0 on 11/16/16.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel getUser(Long userId){
        User user = userRepository.findOne(userId);
        if (user == null){
            throw new UserNotFoundException(userId.toString());
        }
        return new UserModel(user);
    }

    public User add(User user) {
        return userRepository.save(user);
    }
}
