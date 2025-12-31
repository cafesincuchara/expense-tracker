    package com.dev.expensetracker.mappers;

    import com.dev.expensetracker.dtos.CreateUserDto;
    import com.dev.expensetracker.dtos.UserDto;
    import com.dev.expensetracker.entities.User;

    public interface UserMapper {
        UserDto toDto(User user);
        User fromCreateDto(CreateUserDto dto);
    }
