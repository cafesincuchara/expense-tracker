package com.dev.expensetracker.features.auth.controller;

import com.dev.expensetracker.features.auth.service.AuthService;
import com.dev.expensetracker.features.user.dto.CreateUserDto;
import com.dev.expensetracker.features.user.dto.UserDto;
import com.dev.expensetracker.features.user.domain.User;
import com.dev.expensetracker.features.user.mapper.UserMapper;
import com.dev.expensetracker.features.user.repository.UserRepository;
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