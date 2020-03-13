package com.example.usersmicroservice.web.mappers;

import com.example.usersmicroservice.entities.User;
import com.example.usersmicroservice.web.dtos.RegisterUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User registerUserDtoToUser(RegisterUserDto userDto);

}
