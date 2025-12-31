package com.dev.expensetracker.features.user.controller;

import com.dev.expensetracker.features.user.mapper.UserMapper;
import com.dev.expensetracker.features.user.domain.User;
import com.dev.expensetracker.features.user.dto.CreateUserDto;
import com.dev.expensetracker.features.user.dto.UserDto;
import com.dev.expensetracker.features.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService,
                          UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto dto) {
        User user = userMapper.fromCreateDto(dto);
        User saved = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(saved));
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<UserDto>> createUsers(@RequestBody List<CreateUserDto> dtos) {
        List<User> users = dtos.stream()
                .map(userMapper::fromCreateDto)
                .toList();

        List<User> savedUsers = userService.createUsersList(users);

        List<UserDto> response = savedUsers.stream()
                .map(userMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        User user = userService.findById(id);
       return ResponseEntity.ok(userMapper.toDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
