package com.cozy.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int userId;

    private int balance;

    public Account() {}

    public Account(int userId) {
        this.userId = userId;
    }

    public Account(int userId, int balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public Account setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getBalance() {
        return balance;
    }

    public Account setBalance(int balance) {
        this.balance = balance;
        return this;
    }

}
