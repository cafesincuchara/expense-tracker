package com.dev.expensetracker.features.expense.mapper;

import com.dev.expensetracker.features.expense.dto.CreateExpenseDto;
import com.dev.expensetracker.features.expense.dto.ExpenseDto;
import com.dev.expensetracker.features.expense.domain.Expense;
import com.dev.expensetracker.features.category.mapper.CategoryMapper;
import com.dev.expensetracker.features.user.mapper.UserMapper;
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
