package com.cozy.controller;

import com.cozy.common.UserRole;
import com.cozy.model.Token;
import com.cozy.model.User;
import com.cozy.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /* authenticateAdmin: Validates if a user is admin
       Method: POST
       Endpoint: /authenticate/admin
    */
    @PostMapping("/authenticate/admin")
    public Token authenticateAdmin(@RequestBody User user) {
        return authenticationService.authenticate(user, UserRole.ADMIN);
    }

    /* authenticateResident: Validates if a user is resident
       Method: POST
       Endpoint: /authenticate/resident
    */
    @PostMapping("/authenticate/resident")
    public Token authenticateResident(@RequestBody User user) {
        return authenticationService.authenticate(user, UserRole.RESIDENT);
    }
}

