package com.dev.expensetracker.dtos;

public record CreatedUserDto(
        String name,
        String email,
        String password
) {
}
