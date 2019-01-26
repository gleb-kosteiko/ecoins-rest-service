package com.edmunds.ecoins.restservice.repository;

import com.edmunds.ecoins.restservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    User findByUsername(String username);
}
