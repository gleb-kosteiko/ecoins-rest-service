package com.edmunds.ecoins.restservice.service.impl;

import com.edmunds.ecoins.restservice.model.LoginUser;
import com.edmunds.ecoins.restservice.model.Role;
import com.edmunds.ecoins.restservice.model.User;
import com.edmunds.ecoins.restservice.repository.UserRepository;
import com.edmunds.ecoins.restservice.service.UserService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getEncryptedPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        return Sets.newHashSet(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    @Override
    public List<User> findAll() {
        return (List) userRepository.findAll();
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(LoginUser user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEncryptedPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setRole(Role.USER);
        newUser.setCoinsCount(0);
        newUser = userRepository.save(newUser);
        return newUser;
    }
}
