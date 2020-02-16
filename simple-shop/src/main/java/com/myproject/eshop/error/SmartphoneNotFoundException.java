package com.myproject.eshop.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Smartphone not found!")
public class SmartphoneNotFoundException extends RuntimeException {

    public SmartphoneNotFoundException(String message) {
        super(message);
    }
}
