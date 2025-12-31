package com.dev.expensetracker.features.user.mapper;

import com.dev.expensetracker.features.category.dto.CategoryDto;
import com.dev.expensetracker.features.category.dto.CreateCategoryDto;
import com.dev.expensetracker.features.category.domain.Category;
import com.dev.expensetracker.features.category.mapper.CategoryMapper;
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
