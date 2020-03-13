package com.example.usersmicroservice.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidTokenException extends Exception {

    public InvalidTokenException(String message) {
        super(message);
    }

}
