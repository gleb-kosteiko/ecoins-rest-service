package com.edmunds.ecoins.restservice.service;

import com.edmunds.ecoins.restservice.model.UserConfig;

import java.util.List;

public interface UserConfigService {

    UserConfig save(UserConfig publication);
    List<UserConfig> findAll();
    UserConfig findByUserId(String userId);
    UserConfig findById(String id);
    void delete(String id);
}
