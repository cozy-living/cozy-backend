package com.cozy.controller;

import com.cozy.commons.UserRole;
import com.cozy.model.User;
import com.cozy.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegisterController {
    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    /**
     * addUser: User registers as a resident by default
     * Method: POST
     * Endpoint: /register
     */
    @PostMapping("/register")
    public User addUser(@RequestBody User user) {
        return registerService.add(user, UserRole.RESIDENT);
    }

}
