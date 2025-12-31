package com.dev.expensetracker.service;

import com.dev.expensetracker.entities.Category;
import com.dev.expensetracker.entities.Expense;
import com.dev.expensetracker.entities.User;
import com.dev.expensetracker.repository.CategoryRepository;
import com.dev.expensetracker.repository.ExpenseRepository;
import com.dev.expensetracker.repository.UserRepository;
import com.dev.expensetracker.services.impl.ExpenseServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceImplTest {

    @Mock
    private ExpenseRepository expenseRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @Test
    void CreateExpense_ShouldReturnSavedExpense_WhenUserAndCategoryExist() {
        UUID userId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();

        User mockUser = new User();
        Category mockCategory = new Category();
        BigDecimal amount = new BigDecimal("100.00");

        Expense inputExpense = new Expense();
        inputExpense.setAmount(amount);

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));
        when(expenseRepository.save(any(Expense.class))).thenReturn(inputExpense);

        Expense result = expenseService.createExpense(inputExpense, userId, categoryId);

        assertNotNull(result);
        assertEquals(mockUser, result.getUser());
        assertEquals(mockCategory, result.getCategory());

        verify(expenseRepository, times(1)).save(inputExpense);
    }


    @Test
    void FindAllExpenses_ShouldReturnAllExpenses_WhenUserAndCategoryExist() {

        Expense e1 = new Expense();
        e1.setAmount(new BigDecimal("50.00"));
        Expense e2 = new Expense();
        e2.setAmount(new BigDecimal("20.00"));

        when(expenseRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<Expense> result = expenseService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(new BigDecimal("50.00"), result.get(0).getAmount());

        verify(expenseRepository, times(1)).findAll();

    }


    @Test
    void FindById_ShouldReturnExpense_WhenUserAndCategoryExist() {

        Expense e1 = new Expense();

        e1.setAmount(new BigDecimal("50.00"));
        e1.setUser(new User());
        e1.setCategory(new Category());

        when(expenseRepository.findById(e1.getId())).thenReturn(Optional.of(e1));

        assertEquals(e1, expenseService.findById(e1.getId()));
        assertNotNull(e1);

        verify(expenseRepository, times(1)).findById(e1.getId());
    }

    @Test
    void DeleteByIdExpense_ShouldReturnDeletedExpense_WhenUserAndCategoryExist() {

        UUID id = UUID.randomUUID();

        expenseService.deleteById(id);

        verify(expenseRepository, times(1)).deleteById(id);
    }
}