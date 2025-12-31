package com.dev.expensetracker.mappers.impl;

import com.dev.expensetracker.dtos.CategoryDto;
import com.dev.expensetracker.dtos.CreateCategoryDto;
import com.dev.expensetracker.entities.Category;
import com.dev.expensetracker.mappers.CategoryMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {


    @Override
    public CategoryDto toDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    @Override
    public Category fromCreateDto(CreateCategoryDto dto) {
        return new Category(
                null,
                dto.name(),
                dto.description()
        );
    }
}
