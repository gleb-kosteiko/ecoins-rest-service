package com.edmunds.ecoins.restservice.service;

import com.edmunds.ecoins.restservice.model.SignUpUser;
import com.edmunds.ecoins.restservice.model.User;

import java.util.List;

public interface UserService {
    User save(SignUpUser user);
    List<User> findAll();
    void delete(String id);
    User update(User user);
    void addCoins(String id, int coinsCount);
    User findByUsername(String username);
    User findById(String id);
}
