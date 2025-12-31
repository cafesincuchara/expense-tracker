package com.dev.expensetracker.mappers;

import com.dev.expensetracker.dtos.CategoryDto;
import com.dev.expensetracker.dtos.CreateCategoryDto;
import com.dev.expensetracker.entities.Category;

public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category fromCreateDto(CreateCategoryDto dto);
}
