package com.dev.expensetracker.features.category.mapper;

import com.dev.expensetracker.features.user.dto.CreateUserDto;
import com.dev.expensetracker.features.user.dto.UserDto;
import com.dev.expensetracker.features.user.domain.User;
import com.dev.expensetracker.features.user.mapper.UserMapper;
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
