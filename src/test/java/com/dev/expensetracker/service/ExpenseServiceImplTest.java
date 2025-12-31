package com.dev.expensetracker.service;


import com.dev.expensetracker.entities.Category;
import com.dev.expensetracker.entities.Expense;
import com.dev.expensetracker.entities.User;
import com.dev.expensetracker.repository.CategoryRepository;
import com.dev.expensetracker.repository.ExpenseRepository;
import com.dev.expensetracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceImpl {


    @Mock
    private ExpenseRepository expenseRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ExpenseServiceImpl expenseServiceImpl;

    @Test
    void CreateExpense_ShouldReturnSavedExpense_WhenUserAndCategoryExist() {
        UUID userId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();

        User mockUser = new User();
        Category mockCategory = new Category();
        BigDecimal amount = new BigDecimal("-100");

        Expense inputExpense = new Expense();
        inputExpense.setCategory(mockCategory);
        inputExpense.setUser(mockUser);
        inputExpense.setAmount(amount);



        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));

        when(expenseRepository.save(any(Expense.class))).thenReturn(inputExpense);

        Expense result = expenseServiceImpl.createExpense(inputExpense, userId, categoryId);

        assertNotNull(result);
        assertEquals(mockUser, result.getUser()); // Verificamos que el servicio "pegó" el usuario al gasto
        assertEquals(mockCategory, result.getCategory()); // Verificamos que "pegó" la categoría

        // Verificamos que se llamó al save exactamente 1 vez
        verify(expenseRepository, times(1)).save(inputExpense);

    }

}
