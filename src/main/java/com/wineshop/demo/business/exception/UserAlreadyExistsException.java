package com.wineshop.demo.business.exception;

import com.wineshop.demo.business.exception.messages.ExceptionMessages;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super(ExceptionMessages.USER_ALREADY_EXIST);
    }
}
