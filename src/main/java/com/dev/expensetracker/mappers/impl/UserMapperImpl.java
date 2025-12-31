package com.dev.expensetracker.mappers.impl;

import com.dev.expensetracker.dtos.CreateUserDto;
import com.dev.expensetracker.dtos.UserDto;
import com.dev.expensetracker.entities.User;
import com.dev.expensetracker.mappers.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {


    @Override
    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }

    @Override
    public User fromCreateDto(CreateUserDto dto) {
        return new  User(
                null,
                dto.name(),
                dto.email(),
                dto.password(),
                null
        );
    }
}
