package com.zed.foodrecipes.service;

import com.zed.foodrecipes.exception.user.impl.UserAlreadyExistException;
import com.zed.foodrecipes.exception.user.impl.UserEmailAlreadyExistException;
import com.zed.foodrecipes.exception.user.impl.UserNameAlreadyExistException;
import com.zed.foodrecipes.model.User;
import com.zed.foodrecipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Created by Arnaud on 13/03/2016.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserEmailAlreadyExistException();
        }
        if (userRepository.findByName(user.getName()) != null) {
            throw new UserNameAlreadyExistException();
        }
        if ((user.getId() != null && userRepository.exists(user.getId()))) {
            throw new UserAlreadyExistException();
        }

        // Encrypt password
        String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }
}
