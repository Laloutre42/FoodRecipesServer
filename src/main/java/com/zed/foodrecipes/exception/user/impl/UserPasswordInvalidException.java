package com.zed.foodrecipes.exception.user.impl;

import com.zed.foodrecipes.exception.user.UserException;

/**
 * Created by Arnaud on 09/03/2016.
 */
public class UserPasswordInvalidException extends UserException {

    public final static String MESSAGE = "User password is invalid";

    public UserPasswordInvalidException() {
        super(MESSAGE);
    }
}
