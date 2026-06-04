package com.wineshop.demo.business.exception;

import com.wineshop.demo.business.exception.messages.ExceptionMessages;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super(ExceptionMessages.CATEGORY_NOT_FOUND);
    }
}
