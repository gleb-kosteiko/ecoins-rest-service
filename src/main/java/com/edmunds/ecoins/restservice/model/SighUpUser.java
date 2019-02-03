package com.edmunds.ecoins.restservice.model;

import com.edmunds.ecoins.restservice.validation.UniqueUsername;

import javax.validation.constraints.NotBlank;

public class SighUpUser {
    @UniqueUsername
    @NotBlank
    private String username;
    private String password;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
