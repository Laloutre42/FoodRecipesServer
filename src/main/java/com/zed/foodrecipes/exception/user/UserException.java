package com.zed.foodrecipes.exception.user;

/**
 * Created by Arnaud on 09/03/2016.
 */
public class UserException extends RuntimeException {

    public UserException(String msg) {
        super(msg);
    }

    public UserException(String msg, Throwable t) {
        super(msg, t);
    }
}
