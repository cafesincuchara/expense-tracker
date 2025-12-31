package com.dev.expensetracker.services;

import com.dev.expensetracker.entities.Expense;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    Expense createExpense(Expense expense, UUID userId, UUID categoryId);
    List<Expense> findAll();
    Expense findById(UUID id);
    void deleteById(UUID id);
}
