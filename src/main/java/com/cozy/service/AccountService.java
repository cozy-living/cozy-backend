package com.cozy.service;

import com.cozy.model.Account;
import com.cozy.repository.AccountRepository;
import com.cozy.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(UserRepository userRepository,
                       AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public Account get(int userId) {
        return accountRepository.getById(userId);
    }

    public Account put(int userId, int balance) {
        Account account = accountRepository.getById(userId);
        account.setBalance(balance);
        accountRepository.save(account);
        return account;
    }
}
