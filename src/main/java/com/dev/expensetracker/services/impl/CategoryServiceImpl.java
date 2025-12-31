package com.dev.expensetracker.services.impl;

import com.dev.expensetracker.entities.Category;
import com.dev.expensetracker.exceptions.InvalidBusinessLogicException;
import com.dev.expensetracker.exceptions.ResourceNotFoundException;
import com.dev.expensetracker.repository.CategoryRepository;
import com.dev.expensetracker.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private  final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(Category category) {
        if(categoryRepository.existsById(category.getId())){
            throw new InvalidBusinessLogicException("Category with id " + category.getId() + " already exists");
        }
        if(category.getName() == null){
            throw new InvalidBusinessLogicException("Category name is required");
        }
        if(category.getDescription() == null){
            throw new InvalidBusinessLogicException("Category description is required");
        }
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
    }

    @Override
    public void delete(UUID id) {
        categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> createCategoriesList(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

}
