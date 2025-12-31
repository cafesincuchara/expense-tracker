package com.dev.expensetracker.features.category.controller;

import com.dev.expensetracker.features.category.dto.CreateCategoryDto;
import com.dev.expensetracker.features.category.domain.Category;
import com.dev.expensetracker.features.category.dto.CategoryDto;
import com.dev.expensetracker.features.category.mapper.CategoryMapper;
import com.dev.expensetracker.features.category.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CreateCategoryDto dto) {

        Category category = categoryMapper.fromCreateDto(dto);
        Category saved = categoryService.create(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.toDto(saved));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable UUID id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(categoryMapper.toDto(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<CategoryDto>> addCategories(@RequestBody List<CreateCategoryDto> dtos) {
        List<Category> categories = dtos.stream()
                .map(categoryMapper::fromCreateDto)
                .toList();
        List<Category> savedCategories = categoryService.createCategoriesList(categories);
        List<CategoryDto> response = savedCategories.stream()
                .map(categoryMapper::toDto)
                .toList();

        return ResponseEntity.status(201).body(response);
    }
}


