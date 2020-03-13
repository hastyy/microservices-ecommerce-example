package com.example.usersmicroservice.services.impl;

import com.example.usersmicroservice.services.AuthenticationService;
import com.example.usersmicroservice.services.JwtService;
import com.example.usersmicroservice.services.UserService;
import com.example.usersmicroservice.web.dtos.CredentialsDto;
import com.example.usersmicroservice.web.dtos.JwtDto;
import com.example.usersmicroservice.web.dtos.UserDto;
import com.example.usersmicroservice.web.exceptions.InvalidTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public JwtDto authenticate(CredentialsDto credentials) {
        Authentication principal = authenticationManager.authenticate(authenticationToken(credentials));
        String jwt = jwtService.generateToken(principal);

        return new JwtDto(jwt);
    }

    @Override
    public UserDto validate(JwtDto jwtDto) throws InvalidTokenException {
        String jwt = jwtDto.getJwt();

        try {

            if (jwtService.isTokenExpired(jwt))
                throw new InvalidTokenException("Token expired");

            String email = jwtService.extractUsername(jwt);
            Long id = jwtService.extractClaim(jwt, claims -> claims.get("userId", Long.class));

            return UserDto.builder()
                    .id(id)
                    .email(email)
                    .build();

        } catch (RuntimeException ex) {
            throw new InvalidTokenException(ex.getLocalizedMessage());
        }
    }

    @Override
    public UserDetails validate(String jwt) {
        String username = jwtService.extractUsername(jwt);
        UserDetails principal = userService.loadUserByUsername(username);

        return principal;
    }

    private Authentication authenticationToken(CredentialsDto credentials) {
        return new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());
    }

}
