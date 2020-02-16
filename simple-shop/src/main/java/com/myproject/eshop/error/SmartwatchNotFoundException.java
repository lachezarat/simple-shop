package com.myproject.eshop.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Smartwatch not found!")
public class SmartwatchNotFoundException extends RuntimeException {

    public SmartwatchNotFoundException(String message) {
        super(message);
    }
}
