package com.dev.expensetracker.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatedExpenseDto(
        BigDecimal amount,
        String description,
        UUID categoryId
) {
}
