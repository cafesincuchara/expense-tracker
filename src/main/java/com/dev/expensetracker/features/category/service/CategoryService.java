package com.dev.expensetracker.features.category.service;

import com.dev.expensetracker.features.category.domain.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category create(Category category);
    List<Category> findAll();
    Category findById(UUID id);
    void  delete(UUID id);
    List<Category> createCategoriesList( List<Category> categories);

}
