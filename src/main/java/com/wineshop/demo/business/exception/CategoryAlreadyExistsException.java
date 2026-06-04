package com.wineshop.demo.business.exception;
import com.wineshop.demo.business.exception.messages.ExceptionMessages;


public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException() {
        super(ExceptionMessages.CATEGORY_ALREADY_EXIST);
    }
}
