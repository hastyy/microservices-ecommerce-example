package com.example.usersmicroservice.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyInUseException extends Exception {

    public EmailAlreadyInUseException(String email) {
        super(String.format("The email \"%s\" has already been registered", email));
    }

}
