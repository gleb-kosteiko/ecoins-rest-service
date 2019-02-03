package com.edmunds.ecoins.restservice.controller;

import com.edmunds.ecoins.restservice.model.LoggedInUserDto;
import com.edmunds.ecoins.restservice.model.LoginUser;
import com.edmunds.ecoins.restservice.model.SighUpUser;
import com.edmunds.ecoins.restservice.model.User;
import com.edmunds.ecoins.restservice.security.TokenProvider;
import com.edmunds.ecoins.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider jwtTokenUtil;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);

        User user = userService.findByUsername(loginUser.getUsername());

        return ResponseEntity.ok(new LoggedInUserDto(token, user));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> register(@Valid @RequestBody SighUpUser user) {
        User newUser = userService.save(user);
        return new ResponseEntity<>(newUser, newUser != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
