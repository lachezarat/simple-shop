package com.myproject.eshop.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Tablet not found!")
public class TabletNotFoundException extends RuntimeException {

    public TabletNotFoundException(String message) {
        super(message);
    }
}
