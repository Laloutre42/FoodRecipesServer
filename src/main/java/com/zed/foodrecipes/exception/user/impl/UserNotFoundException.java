package com.zed.foodrecipes.exception.user.impl;

import com.zed.foodrecipes.exception.user.UserException;

/**
 * Created by Arnaud on 09/03/2016.
 */
public class UserNotFoundException extends UserException {

    public final static String MESSAGE = "User does not exist in database";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
