package com.dev.expensetracker.mappers;

import com.dev.expensetracker.dtos.CreateExpenseDto;
import com.dev.expensetracker.dtos.ExpenseDto;
import com.dev.expensetracker.entities.Expense;

public interface ExpenseMapper {
    ExpenseDto toDto (Expense expense);
    Expense fromCreateDto(CreateExpenseDto dto);
}
