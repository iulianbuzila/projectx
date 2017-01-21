package com.projectx.cwm.services;

import com.google.common.collect.Lists;
import com.mysql.jdbc.StringUtils;
import com.projectx.cwm.domain.Log;
import com.projectx.cwm.domain.User;
import com.projectx.cwm.exceptions.ResourceAlreadyExists;
import com.projectx.cwm.exceptions.ResourceNotFound;
import com.projectx.cwm.exceptions.UserNotFoundException;
import com.projectx.cwm.models.UserLoginDetails;
import com.projectx.cwm.models.UserModel;
import com.projectx.cwm.repositories.LogRepository;
import com.projectx.cwm.repositories.UserRepository;
import it.ozimov.springboot.templating.mail.model.Email;
import it.ozimov.springboot.templating.mail.model.impl.EmailImpl;
import it.ozimov.springboot.templating.mail.service.EmailService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sl0 on 11/16/16.
 */
@Service
public class UserService {
    private final Logger logger = Logger.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final EmailService emailService;

    private final LogRepository logRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService, LogRepository logRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
        this.logRepository = logRepository;
    }


    public UserModel add(UserModel userModel, UserLoginDetails loggedUser) {
        User user = userRepository.findByUsername(userModel.getUsername());
        if (user != null){
            logger.error("User " + userModel.getUsername() + " already exists!");
            throw new ResourceAlreadyExists(userModel.getUsername());
        }
        User newUser = new User();
        newUser.setUsername(userModel.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        newUser.setEmail(userModel.getEmail());
//        newUser.setRole(userModel.getRole());
        newUser = userRepository.save(newUser);
        userRepository.flush();
        logRepository.save(new Log(null, "User " + loggedUser.getUsername() + " added user " +
                newUser.getUsername() + ".", userRepository.findByUsername(loggedUser.getUsername())));
        return new UserModel(newUser, userRepository.findRolesByUser(newUser.getUsername()));
    }

    public boolean delete(Long userId, UserLoginDetails loggedUser){
        User user = userRepository.findOne(userId);

        if (user == null){
            logger.error("User " + userId.toString() + " not found!");
            throw new ResourceNotFound("user " + userId.toString());
        }

        userRepository.delete(user);
        userRepository.flush();

        logRepository.save(new Log(null, "User " + loggedUser.getUsername() + " deleted user " +
                user.getUsername() + ".", userRepository.findByUsername(loggedUser.getUsername())));
        return true;
    }

    public UserModel getUser(Long userId, UserLoginDetails loggedUser) {
        User user = userRepository.findOne(userId);
        if (user == null){
            logger.error("User " + userId.toString() + " not found!");
            throw new UserNotFoundException(userId.toString());
        }

        logRepository.save(new Log(null, "User " + loggedUser.getUsername() + " got user " +
                user.getUsername() + ".", userRepository.findByUsername(loggedUser.getUsername())));
        return new UserModel(user, userRepository.findRolesByUser(user.getUsername()));
    }

    public UserModel edit(UserModel userModel, Long userId, UserLoginDetails loggedUser){
        User user = userRepository.findOne(userId);
        if (user == null){
            logger.error("User " + userId.toString() + " not found!");
            throw new UserNotFoundException(userId.toString());
        }

        user.setUsername(userModel.getUsername());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        if (!StringUtils.isNullOrEmpty(userModel.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        }

//        user.setRole(userModel.getRole());
        userRepository.save(user);
        userRepository.flush();

        logRepository.save(new Log(null, "User " + loggedUser.getUsername() + " edited user " +
                user.getUsername() + ".", userRepository.findByUsername(loggedUser.getUsername())));

        return new UserModel(user, userRepository.findRolesByUser(user.getUsername()));
    }

    public Set<UserModel> getUsers(UserLoginDetails loggedUser) {
        logRepository.save(new Log(null, "User " + loggedUser.getUsername() + " got all users " + ".",
                userRepository.findByUsername(loggedUser.getUsername())));
        return userRepository.findAll().stream().map(UserModel::new).collect(Collectors.toSet());
    }

    public UserModel forgotPassword(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null){
            logger.error("User with " + email + " not found!");
            throw new UserNotFoundException(email);
        }

        String uncryptedPassword = RandomStringUtils.randomAlphanumeric(8);
        String newPassword = bCryptPasswordEncoder.encode(uncryptedPassword);
        user.setPassword(newPassword);

        userRepository.save(user);
        userRepository.flush();
        logRepository.save(new Log(null, "User " + user.getUsername() + " got all users " + ".",
                user));
        try {
            sendEmail(user, uncryptedPassword);
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return new UserModel(user, userRepository.findRolesByUser(user.getUsername()));
    }

    public void sendEmail(User user, String uncryptedPassword) throws AddressException {
        final Email email = EmailImpl.builder()
                .from(new InternetAddress("support@projectx.com"))
                .to(Lists.newArrayList(new InternetAddress(user.getEmail())))
                .subject("Reset Password")
                .body("You forgot password. This is your new password: " + uncryptedPassword)
                .encoding(Charset.forName("UTF-8")).build();

        emailService.send(email);
    }
}
