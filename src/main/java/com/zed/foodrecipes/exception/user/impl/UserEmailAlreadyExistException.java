package com.zed.foodrecipes.exception.user.impl;

import com.zed.foodrecipes.exception.user.UserException;

/**
 * Created by Arnaud on 09/03/2016.
 */
public class UserEmailAlreadyExistException extends UserException {

    public final static String MESSAGE = "User email already exist in database";

    public UserEmailAlreadyExistException() {
        super(MESSAGE);
    }
}
