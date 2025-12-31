package com.dev.expensetracker.features.expense.repository;

import com.dev.expensetracker.features.expense.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
}
