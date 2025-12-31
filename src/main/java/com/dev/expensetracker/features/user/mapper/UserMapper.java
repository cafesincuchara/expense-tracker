    package com.dev.expensetracker.features.user.mapper;

    import com.dev.expensetracker.features.user.domain.User;
    import com.dev.expensetracker.features.user.dto.CreateUserDto;
    import com.dev.expensetracker.features.user.dto.UserDto;

    public interface UserMapper {
        UserDto toDto(User user);
        User fromCreateDto(CreateUserDto dto);
    }
