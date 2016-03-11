package com.zed.foodrecipes.exception.user.impl;

import com.zed.foodrecipes.exception.user.UserException;

/**
 * Created by Arnaud on 09/03/2016.
 */
public class UserNameAlreadyExistException extends UserException {

    public final static String MESSAGE = "User name already exist in database";

    public UserNameAlreadyExistException() {
        super(MESSAGE);
    }
}
