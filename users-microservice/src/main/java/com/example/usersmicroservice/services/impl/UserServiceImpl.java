package com.example.usersmicroservice.services.impl;

import com.example.usersmicroservice.entities.User;
import com.example.usersmicroservice.repositories.UserRepository;
import com.example.usersmicroservice.services.UserService;
import com.example.usersmicroservice.web.dtos.RegisterUserDto;
import com.example.usersmicroservice.web.exceptions.EmailAlreadyInUseException;
import com.example.usersmicroservice.web.mappers.UserMapper;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterUserDto userDto) throws EmailAlreadyInUseException {
        try {
            User user = userMapper.registerUserDtoToUser(userDto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.saveAndFlush(user);

            return savedUser;
        } catch (DataIntegrityViolationException ex) {
            throw new EmailAlreadyInUseException(userDto.getEmail());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .map(Principal::new)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user in the database"));
    }


    @Getter
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class Principal implements UserDetails {

        private User userEntity;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.emptySet();
        }

        @Override
        public String getPassword() {
            return userEntity.getPassword();
        }

        @Override
        public String getUsername() {
            return userEntity.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

    }
}
