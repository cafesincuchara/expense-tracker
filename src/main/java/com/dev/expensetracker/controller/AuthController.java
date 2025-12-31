package com.dev.expensetracker.controller;

import com.dev.expensetracker.dtos.CreateUserDto;
import com.dev.expensetracker.dtos.UserDto;
import com.dev.expensetracker.entities.User;
import com.dev.expensetracker.mappers.UserMapper;
import com.dev.expensetracker.repository.UserRepository;
import com.dev.expensetracker.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthController(AuthService authService, UserRepository userRepository, UserMapper userMapper) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(authService.register(createUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("No active authentication detected");
        }

        String receivedEmail = principal.getName();
        System.out.println("DEBUG: Email extracted from Principal: [" + receivedEmail + "]");

        User user = userRepository.findByEmail(receivedEmail)
                .orElseThrow(() -> new RuntimeException("User not found in database: " + receivedEmail));

        return ResponseEntity.ok(userMapper.toDto(user));
    }
}