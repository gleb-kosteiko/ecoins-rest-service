package com.edmunds.ecoins.restservice.controller;

import com.edmunds.ecoins.restservice.model.User;
import com.edmunds.ecoins.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getById(@PathVariable(value = "id") String id) {
        return userService.findById(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public User update(@NotBlank @PathVariable("id") String id,
                       @Valid @RequestBody User user) {
        User existingUser = userService.findById(id);
        if (!Objects.isNull(user.getUsername())) {
            existingUser.setUsername(user.getUsername());
        }
        if (!Objects.isNull(user.getEmail())) {
            existingUser.setEmail(user.getEmail());
        }
        if (!Objects.isNull(user.getCity())) {
            existingUser.setCity(user.getCity());
        }
        if (!Objects.isNull(user.getCountry())) {
            existingUser.setCountry(user.getCountry());
        }
        if (!Objects.isNull(user.getImageUrl())) {
            existingUser.setImageUrl(user.getImageUrl());
        }
        if (!Objects.isNull(user.getRole())) {
            existingUser.setRole(user.getRole());
        }

        return userService.update(existingUser);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(value = "id") String id) {
        userService.delete(id);
    }
}
