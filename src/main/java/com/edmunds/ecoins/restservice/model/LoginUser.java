package com.edmunds.ecoins.restservice.model;

import com.edmunds.ecoins.restservice.validation.UniqueUsername;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginUser {
    @UniqueUsername
    @NotBlank
    private String username;
//    @Pattern(regexp = "((?=.*[a-z])(?=.*d)(?=.*[!№;:?*()@#$%])(?=.*[A-Z]).{6,})",
//            message = "At least 1 digit, at least one lower case letter, at least one upper case letter, at least one special character, at least 6 characters")
    private String password;

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
}
