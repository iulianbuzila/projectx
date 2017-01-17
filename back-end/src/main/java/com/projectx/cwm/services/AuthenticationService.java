package com.projectx.cwm.services;

import com.projectx.cwm.domain.User;
import com.projectx.cwm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by sl0 on 1/16/17.
 */
@Service
public class AuthenticationService {
    @Autowired
    UserRepository userRepository;

    @Value("${auth.tokenTimeout}")
    String accessTokenTtl;

    @Value("${auth.refreshTokenTimeout}")
    String refreshTokenTtl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return false;
        }

        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }
}
