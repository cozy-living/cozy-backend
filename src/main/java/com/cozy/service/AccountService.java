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

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account get(int userId) {
        return accountRepository.getByUserId(userId);
    }

    public Account put(int userId, int balance) {
        Account account = accountRepository.getByUserId(userId);
        account.setBalance(balance);
        accountRepository.save(account);
        return account;
    }
}
