package com.edmunds.ecoins.restservice.service;

import com.edmunds.ecoins.restservice.model.LoginUser;
import com.edmunds.ecoins.restservice.model.User;

import java.util.List;

public interface UserService {
    User save(LoginUser user);
    List<User> findAll();
    void delete(String id);
    User update(User user);
    User findByUsername(String username);
    User findById(String id);
}
