package com.dev.expensetracker.mappers.impl;

import com.dev.expensetracker.dtos.CreateExpenseDto;
import com.dev.expensetracker.dtos.ExpenseDto;
import com.dev.expensetracker.entities.Expense;
import com.dev.expensetracker.mappers.CategoryMapper;
import com.dev.expensetracker.mappers.ExpenseMapper;
import com.dev.expensetracker.mappers.UserMapper;
import org.springframework.stereotype.Component;


@Component
public class ExpenseMapperImpl implements ExpenseMapper {

    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;

    public ExpenseMapperImpl(UserMapper userMapper, CategoryMapper categoryMapper) {
        this.userMapper = userMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ExpenseDto toDto(Expense expense) {
        return new ExpenseDto(
                expense.getId(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getDate(),
                categoryMapper.toDto(expense.getCategory()),
                userMapper.toDto(expense.getUser())
        );
    }

    @Override
    public Expense fromCreateDto(CreateExpenseDto dto) {
        Expense expense = new Expense();
        expense.setAmount(dto.amount());
        expense.setDescription(dto.description());
        return expense;
    }


}
