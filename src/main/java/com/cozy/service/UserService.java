package com.cozy.service;

import com.cozy.commons.UserRole;
import com.cozy.exception.UserAlreadyExistException;
import com.cozy.exception.UserNotExistException;
import com.cozy.model.Account;
import com.cozy.model.User;
import com.cozy.repository.AccountRepository;
import com.cozy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public User add(User user, UserRole role) throws UserAlreadyExistException {
        if (userRepository.existsByUsername(user.getUsername())) {
            log.error("The username already exists!");
            throw new UserAlreadyExistException("The username already exists!");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            log.error("The email already exists!");
            throw new UserAlreadyExistException("The email already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role.name());
//        user.setReservations(new ArrayList<>());
        userRepository.save(user);
        accountRepository.save(new Account(user.getId()));
        return user;
    }

    public User get(int userId) throws UserNotExistException {
        if (userRepository.findById(userId) == null) {
            log.error("The user does not exist!");
            throw new UserNotExistException("The user does not exist!");
        }
        return userRepository.findById(userId);
    }

}