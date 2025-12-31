package com.dev.expensetracker.features.category.dto;

public record CreateCategoryDto(
        String name,
        String description
) {}