package com.myproject.eshop.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Laptop not found!")
public class LaptopNotFoundException extends RuntimeException {

    public LaptopNotFoundException(String message) {
        super(message);
    }
}
