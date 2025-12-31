package com.dev.expensetracker.features.auth.service;

import com.dev.expensetracker.features.user.dto.CreateUserDto;
import com.dev.expensetracker.features.user.dto.UserDto;

public interface AuthService {
    UserDto register(CreateUserDto createUserDto);

}