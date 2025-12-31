package com.dev.expensetracker.features.category.mapper;

import com.dev.expensetracker.features.category.dto.CategoryDto;
import com.dev.expensetracker.features.category.dto.CreateCategoryDto;
import com.dev.expensetracker.features.category.domain.Category;

public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category fromCreateDto(CreateCategoryDto dto);
}
