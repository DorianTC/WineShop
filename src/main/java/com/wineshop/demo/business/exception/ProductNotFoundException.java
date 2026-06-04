package com.wineshop.demo.business.exception;

import com.wineshop.demo.business.exception.messages.ExceptionMessages;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super(ExceptionMessages.PRODUCT_NOT_FOUND);
    }
}
