package com.cozy.service;

import com.cozy.common.UserRole;
import com.cozy.exception.UserAlreadyExistException;
import com.cozy.model.Account;
import com.cozy.model.User;
import com.cozy.repository.AccountRepository;
import com.cozy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(UserRepository userRepository,
                           AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void add(User user, UserRole role) throws UserAlreadyExistException {
        if (userRepository.existsById(user.getUserId())) {
            throw new UserAlreadyExistException("User already exists!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role.name());
        userRepository.save(user);
        accountRepository.save(new Account(user.getUserId()));
    }

}