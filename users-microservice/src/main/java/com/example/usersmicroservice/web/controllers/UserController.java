package com.example.usersmicroservice.web.controllers;

import com.example.usersmicroservice.entities.User;
import com.example.usersmicroservice.services.UserService;
import com.example.usersmicroservice.web.dtos.RegisterUserDto;
import com.example.usersmicroservice.web.exceptions.EmailAlreadyInUseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/users";

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@Valid @RequestBody RegisterUserDto userDto) throws EmailAlreadyInUseException {
        log.info("Registering user: {}", userDto.getEmail());

        User registeredUser = userService.registerUser(userDto);

        log.info("Registered user: {}", registeredUser.getEmail());

        return registeredUser;
    }

}
