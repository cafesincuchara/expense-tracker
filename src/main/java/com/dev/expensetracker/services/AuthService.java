package com.dev.expensetracker.services;

import com.dev.expensetracker.dtos.CreateUserDto;
import com.dev.expensetracker.dtos.UserDto;
import org.springframework.stereotype.Service;

public interface AuthService {
    UserDto register(CreateUserDto createUserDto);

}