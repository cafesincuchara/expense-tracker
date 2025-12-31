package com.dev.expensetracker.features.auth.service;

import com.dev.expensetracker.features.user.dto.CreateUserDto;
import com.dev.expensetracker.features.user.dto.UserDto;
import com.dev.expensetracker.features.user.domain.User;
import com.dev.expensetracker.features.user.mapper.UserMapper;
import com.dev.expensetracker.features.user.repository.UserRepository;
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