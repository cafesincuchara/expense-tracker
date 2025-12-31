package com.dev.expensetracker.services;

import com.dev.expensetracker.entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category create(Category category);
    List<Category> findAll();
    Category findById(UUID id);
    void  delete(UUID id);
    List<Category> createCategoriesList( List<Category> categories);

}
