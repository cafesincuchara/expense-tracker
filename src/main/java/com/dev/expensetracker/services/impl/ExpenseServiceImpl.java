package com.dev.expensetracker.services.impl;

import com.dev.expensetracker.entities.Category;
import com.dev.expensetracker.entities.Expense;
import com.dev.expensetracker.entities.User;
import com.dev.expensetracker.repository.CategoryRepository;
import com.dev.expensetracker.repository.ExpenseRepository;
import com.dev.expensetracker.repository.UserRepository;
import com.dev.expensetracker.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpensiveServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Expense createExpense(Expense expense, UUID userId, UUID categoryId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryId));
        expense.setUser(user);
        expense.setCategory(category);
        expenseRepository.set(expense);
        expenseRepository.save(expense);
        return null;
    }

    @Override
    public List<Expense> findAllExpenses() {
        return List.of();
    }

    @Override
    public List<Expense> findByUser(UUID userId) {
        return List.of();
    }

    @Override
    public List<Expense> findByCategory(UUID categoryId) {
        return List.of();
    }

    @Override
    public void delete(UUID id) {

    }
}
