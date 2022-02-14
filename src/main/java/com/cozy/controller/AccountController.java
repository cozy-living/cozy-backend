package com.cozy.controller;

import com.cozy.model.Account;
import com.cozy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * getBalance: Lists a user's account to see the amount of balance
     * Method: GET
     * Endpoint: /{userId}/account
     */
    @GetMapping("/{userId}/account")
    public Account getAccount(@PathVariable(value = "userId") int userId) {
        return accountService.get(userId);
    }

    /**
     * payBalance: Set the amount of balance in a user's account for payment
     * Method: PUT
     * Endpoint: /{userId}/account
     */
    @PutMapping("/{userId}/account/{balance}")
    public Account payBalance(@PathVariable("userId") int userId,
                              @PathVariable("balance") int balance) {
        return accountService.put(userId, balance);
    }
}
