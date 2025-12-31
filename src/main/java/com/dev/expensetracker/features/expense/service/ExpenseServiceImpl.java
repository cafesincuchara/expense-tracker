package com.dev.expensetracker.features.expense.service;

import com.dev.expensetracker.features.category.domain.Category;
import com.dev.expensetracker.features.expense.domain.Expense;
import com.dev.expensetracker.features.user.domain.User;
import com.dev.expensetracker.common.exceptions.InvalidBusinessLogicException;
import com.dev.expensetracker.common.exceptions.ResourceNotFoundException;
import com.dev.expensetracker.features.category.repository.CategoryRepository;
import com.dev.expensetracker.features.expense.repository.ExpenseRepository;
import com.dev.expensetracker.features.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Expense createExpense(Expense expense, UUID userId, UUID categoryId) {
        if (expense.getAmount() == null || expense.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidBusinessLogicException("Expense amount must be positive and greater than zero");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryId));

        expense.setUser(user);
        expense.setCategory(category);

        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense findById(UUID id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found: " + id));
    }

    @Override
    public void deleteById(UUID id) {
        if(expenseRepository.findById(id).isPresent()) {
            expenseRepository.deleteById(id);
        }
        else {
        throw new ResourceNotFoundException("Expense not found: " + id);}
    }
}