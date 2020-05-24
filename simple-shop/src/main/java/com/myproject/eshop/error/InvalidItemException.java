package com.myproject.eshop.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid item!")
public class InvalidItemException extends RuntimeException {

    public InvalidItemException(String message) {
        super(message);
    }
}
