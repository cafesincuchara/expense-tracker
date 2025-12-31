package com.dev.expensetracker.controller;

import com.dev.expensetracker.dtos.CreateExpenseDto;
import com.dev.expensetracker.dtos.ExpenseDto;
import com.dev.expensetracker.entities.Expense;
import com.dev.expensetracker.mappers.ExpenseMapper;
import com.dev.expensetracker.services.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseMapper expenseMapper;

    public ExpenseController(ExpenseService expenseService, ExpenseMapper expenseMapper) {
        this.expenseService = expenseService;
        this.expenseMapper = expenseMapper;
    }


    @PostMapping("/{id}")
    public ResponseEntity<ExpenseDto> createExpense(@PathVariable UUID id, @RequestBody CreateExpenseDto dto) {
        Expense expense = expenseMapper.fromCreateDto(dto);
        Expense saved = expenseService.createExpense(expense, id, dto.categoryId());
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseMapper.toDto(saved));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> findAll() {
        List<ExpenseDto> expenses = expenseService.findAll().stream().map(expenseMapper::toDto).toList();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(expenseMapper.toDto(expenseService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        expenseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
