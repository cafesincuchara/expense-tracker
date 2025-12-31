package com.dev.expensetracker.dtos;

public record CreateUserDto(
        String name,
        String email,
        String password
) {
}
