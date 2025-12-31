package com.dev.expensetracker.features.expense.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateExpenseDto(
        BigDecimal amount,
        String description,
        UUID categoryId
) {
}
