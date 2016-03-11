package com.zed.foodrecipes.controller.exception;

import com.zed.foodrecipes.exception.user.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 */
@ControllerAdvice
public class UserExceptionHandlerController extends ResponseEntityExceptionHandler {

    /**
     * Class Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(UserExceptionHandlerController.class);

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = UserEmailAlreadyExistException.MESSAGE)
    @ExceptionHandler({UserEmailAlreadyExistException.class})
    public String handleUserAlreadyExistException(UserEmailAlreadyExistException exception) {
        logger.error("[handleUserAlreadyExistException] " + exception.getMessage());
        return exception.getMessage();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = UserNameAlreadyExistException.MESSAGE)
    @ExceptionHandler({UserNameAlreadyExistException.class})
    public String handleUserNameAlreadyExistException(UserNameAlreadyExistException exception) {
        logger.error("[handleUserNameAlreadyExistException] " + exception.getMessage());
        return exception.getMessage();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = UserAlreadyExistException.MESSAGE)
    @ExceptionHandler({UserAlreadyExistException.class})
    public String handleUserExistException(UserAlreadyExistException exception) {
        logger.error("[handleUserExistException] " + exception.getMessage());
        return exception.getMessage();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = UserNotFoundException.MESSAGE)
    @ExceptionHandler({UserNotFoundException.class})
    public String handleUserNotFoundException(UserNotFoundException exception) {
        logger.error("[handleUserNotFoundException] " + exception.getMessage());
        return exception.getMessage();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = UserPasswordInvalidException.MESSAGE)
    @ExceptionHandler({UserPasswordInvalidException.class})
    public String handleUserPasswordDoesNotExistException(UserPasswordInvalidException exception) {
        logger.error("[handleUserPasswordDoesNotExistException] " + exception.getMessage());
        return exception.getMessage();
    }

}
