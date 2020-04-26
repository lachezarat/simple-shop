package com.myproject.eshop.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "User was blocked!")
public class UserWasBlockedException extends RuntimeException {

    public UserWasBlockedException(String message) {
        super(message);
    }
}
