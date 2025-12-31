package com.dev.expensetracker.repository;

import com.dev.expensetracker.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ExpensiveRepository extends JpaRepository<Expense, UUID> {
}
