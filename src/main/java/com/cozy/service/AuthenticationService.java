package com.cozy.service;

import com.cozy.commons.UserRole;
import com.cozy.exception.UserNotExistException;
import com.cozy.model.Token;
import com.cozy.model.User;
import com.cozy.repository.UserRepository;
import com.cozy.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;


@Service
public class AuthenticationService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager,
                                 UserRepository userRepository, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public Token authenticate(User user, UserRole role) throws UserNotExistException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (AuthenticationException exception) {
            throw new UserNotExistException("The username and password do not match!");
        }

        User authorizedUser = userRepository.findByUsername(user.getUsername());

        if (!authorizedUser.getRole().equals(role.name())) {
            throw new UserNotExistException("The user account is not authorized!");
        }

        return new Token(jwtUtil.generateToken(user.getUsername()));
    }

}

