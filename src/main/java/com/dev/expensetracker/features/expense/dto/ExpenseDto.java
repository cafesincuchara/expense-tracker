package com.dev.expensetracker.features.expense.dto;


import com.dev.expensetracker.features.category.dto.CategoryDto;
import com.dev.expensetracker.features.user.dto.UserDto;

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
