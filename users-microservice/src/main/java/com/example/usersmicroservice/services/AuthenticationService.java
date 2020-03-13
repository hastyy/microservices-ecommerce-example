package com.example.usersmicroservice.services;

import com.example.usersmicroservice.web.dtos.CredentialsDto;
import com.example.usersmicroservice.web.dtos.JwtDto;
import com.example.usersmicroservice.web.dtos.UserDto;
import com.example.usersmicroservice.web.exceptions.InvalidTokenException;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    JwtDto authenticate(CredentialsDto credentials);

    UserDto validate(JwtDto jwt) throws InvalidTokenException;

    UserDetails validate(String jwt);

}
