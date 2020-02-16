package com.myproject.eshop.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Television not found!")
public class TelevisionNotFoundException extends RuntimeException {

    public TelevisionNotFoundException(String message) {
        super(message);
    }
}
