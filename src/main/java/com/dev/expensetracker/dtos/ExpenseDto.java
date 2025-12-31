package com.dev.expensetracker.dtos;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ExpenseDto(
        UUID id,
        BigDecimal amount,
        String description,
        LocalDateTime date,
        CategoryDto category,
        UserDto user

) {
}
