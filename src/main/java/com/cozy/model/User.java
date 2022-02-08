package com.cozy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@JsonDeserialize(builder = User.Builder.class)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private String suite;

    private String role;

    public User() {}

    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
        this.suite = builder.suite;
        this.role = builder.role;
    }

    public int getUserId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSuite() {
        return suite;
    }

    public User setSuite(String suite) {
        this.suite = suite;
        return this;
    }

    public String getRole() {
        return role;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }


    public static class Builder {
        @JsonProperty("id")
        public int id;

        @JsonProperty("username")
        private String username;

        @JsonProperty("email")
        private String email;

        @JsonProperty("password")
        private String password;

        @JsonProperty("suite")
        private String suite;

        @JsonProperty("role")
        private String role;

        @JsonProperty("accountId")
        private int accountId;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setSuite(String suite) {
            this.suite = suite;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}

