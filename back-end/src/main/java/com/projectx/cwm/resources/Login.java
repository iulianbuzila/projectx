package com.projectx.cwm.resources;

import com.projectx.cwm.models.UserLoginDetails;
import com.projectx.cwm.security.JwtAuthenticationResponse;
import com.projectx.cwm.security.JwtTokenUtil;
import com.projectx.cwm.services.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sl0 on 11/17/16.
 */

@RestController
@RequestMapping("/api/login")
public class Login {
    Logger logger = Logger.getLogger(Login.class);


    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    LoginService loginService;

    @Autowired
    public Login(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody UserLoginDetails user) {
        boolean auth = loginService.logIn(user);
        if (auth) {

            // Perform the security
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);

            // Return the token
            return ResponseEntity.ok(new JwtAuthenticationResponse(token,userDetails.getAuthorities()));

        }
        throw new BadCredentialsException("Bad Login");

    }
}
