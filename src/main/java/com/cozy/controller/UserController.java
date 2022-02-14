package com.cozy.controller;

import com.cozy.commons.UserRole;
import com.cozy.model.User;
import com.cozy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * addUser: User registers as a resident by default
     * Method: POST
     * Endpoint: /register
     */
    @PostMapping("/register")
    public User addUser(@RequestBody User user) {
        return userService.add(user, UserRole.RESIDENT);
    }

}
