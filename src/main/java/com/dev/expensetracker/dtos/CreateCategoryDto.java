package com.dev.expensetracker.dtos;

public record CreateCategoryDto(
        String name,
        String description
) {}