package com.edmunds.ecoins.restservice.model;

public class LoggedInUserDto {
    private String token;
    private User user;

    public LoggedInUserDto(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public LoggedInUserDto() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
