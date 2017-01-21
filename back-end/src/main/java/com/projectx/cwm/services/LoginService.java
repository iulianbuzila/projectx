package com.projectx.cwm.services;

import com.projectx.cwm.domain.User;
import com.projectx.cwm.models.UserLoginDetails;
import com.projectx.cwm.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sl0 on 11/16/16.
 */
@Service
public class LoginService implements UserDetailsService {
    private final UserRepository userRepository;
    private final Logger logger = Logger.getLogger(LoginService.class);
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        List<String> roles = userRepository.findRolesByUser(s);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        if (roles != null) {
            for (String role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                authorities.add(authority);
            }
        }
        String username = "";
        String password = "";
        if (user != null) {
            username = user.getUsername();
            password = user.getPassword();
        }
        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }

    public boolean logIn(UserLoginDetails userModel) {
        User user = userRepository.findByUsername(userModel.getUsername());
        if (user == null) {
            logger.error("Unsuccessful login " + userModel.getUsername().toString() + ".");
            return false;
        }
        return bCryptPasswordEncoder.matches(userModel.getPassword(), user.getPassword());
    }
}
