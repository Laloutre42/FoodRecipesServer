package com.zed.foodrecipes.controller;

import com.zed.foodrecipes.exception.user.impl.*;
import com.zed.foodrecipes.model.User;
import com.zed.foodrecipes.repository.UserRepository;
import com.zed.foodrecipes.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Arnaud on 01/05/2015.
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    /**
     * Class Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody User user) {

        User userInDb = userRepository.findByEmail(user.getEmail());
        if (userInDb == null) {
            throw new UserNotFoundException();
        }

        // Check password
        if (!BCrypt.checkpw(user.getPassword(), userInDb.getPassword())) {
            throw new UserPasswordInvalidException();
        }

        return userInDb;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public User signUp(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping("/principalUser")
    public Principal user(Principal user) {
        return user;
    }
}
