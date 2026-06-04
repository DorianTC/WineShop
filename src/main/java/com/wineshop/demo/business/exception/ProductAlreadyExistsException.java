package com.wineshop.demo.business.exception;
import com.wineshop.demo.business.exception.messages.ExceptionMessages;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException() {
       super(ExceptionMessages.PRODUCT_ALREADY_EXIST);
    }
}

