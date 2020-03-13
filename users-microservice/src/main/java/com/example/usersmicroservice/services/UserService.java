package com.example.usersmicroservice.services;

import com.example.usersmicroservice.entities.User;
import com.example.usersmicroservice.web.dtos.RegisterUserDto;
import com.example.usersmicroservice.web.exceptions.EmailAlreadyInUseException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User registerUser(RegisterUserDto userDto) throws EmailAlreadyInUseException;

}
