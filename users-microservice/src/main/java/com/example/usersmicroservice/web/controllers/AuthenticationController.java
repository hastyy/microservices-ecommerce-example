package com.example.usersmicroservice.web.controllers;

import com.example.usersmicroservice.entities.User;
import com.example.usersmicroservice.services.AuthenticationService;
import com.example.usersmicroservice.services.UserService;
import com.example.usersmicroservice.web.dtos.CredentialsDto;
import com.example.usersmicroservice.web.dtos.JwtDto;
import com.example.usersmicroservice.web.dtos.UserDto;
import com.example.usersmicroservice.web.exceptions.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(AuthenticationController.BASE_URL)
public class AuthenticationController {

    public static final String BASE_URL = "/authentication";

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtDto authenticate(@Valid @RequestBody CredentialsDto credentials) {
        log.info("Authenticating user: {}", credentials.getEmail());

        JwtDto jwt = authenticationService.authenticate(credentials);

        log.info("Authenticated user: {}", credentials.getEmail());

        return jwt;
    }

    @PostMapping("/validate")
    @ResponseStatus(HttpStatus.OK)
    public UserDto validateToken(@Valid @RequestBody JwtDto token) throws InvalidTokenException {

        log.info("Validating token: {}", token);

        UserDto user = authenticationService.validate(token);

        log.info("User {} matches token {}", user, token);

        return user;
    }

}
