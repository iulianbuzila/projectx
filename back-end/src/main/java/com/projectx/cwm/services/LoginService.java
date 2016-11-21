package com.projectx.cwm.services;

import com.projectx.cwm.models.UserLoginDetails;
import com.projectx.cwm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by sl0 on 11/16/16.
 */
@Service
public class LoginService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new UserLoginDetails(userRepository.findByUsername(s).get());
    }
}
