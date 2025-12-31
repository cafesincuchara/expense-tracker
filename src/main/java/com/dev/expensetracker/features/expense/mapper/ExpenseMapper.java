package com.dev.expensetracker.features.expense.mapper;

import com.dev.expensetracker.features.expense.domain.Expense;
import com.dev.expensetracker.features.expense.dto.CreateExpenseDto;
import com.dev.expensetracker.features.expense.dto.ExpenseDto;

public interface ExpenseMapper {
    ExpenseDto toDto (Expense expense);
    Expense fromCreateDto(CreateExpenseDto dto);
}
