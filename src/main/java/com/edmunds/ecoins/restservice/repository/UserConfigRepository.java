package com.edmunds.ecoins.restservice.repository;

import com.edmunds.ecoins.restservice.model.UserConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConfigRepository extends CrudRepository<UserConfig, String> {

    UserConfig findByUserId(String userId);
}
