package com.dev.expensetracker.services.impl;

import com.dev.expensetracker.dtos.CreateUserDto;
import com.dev.expensetracker.dtos.UserDto;
import com.dev.expensetracker.entities.User;
import com.dev.expensetracker.mappers.UserMapper;
import com.dev.expensetracker.repository.UserRepository;
import com.dev.expensetracker.services.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto register(CreateUserDto createUserDto) {
        if (userRepository.existsByEmail(createUserDto.email())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByName(createUserDto.name())) {
            throw new RuntimeException("Username already exists");
        }

        User user = userMapper.fromCreateDto(createUserDto);

        user.setPassword(passwordEncoder.encode(createUserDto.password()));

        user.setName(createUserDto.name());
        user.setEmail(createUserDto.email());
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }



}