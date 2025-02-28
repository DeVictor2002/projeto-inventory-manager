package com.victor.inventoryManager.controller;

import com.victor.inventoryManager.dto.CategoryDto;
import com.victor.inventoryManager.dto.CreateCategoryDto;
import com.victor.inventoryManager.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        CategoryDto categoryDto = categoryService.createCategory(createCategoryDto);
        URI location = URI.create("/api/v1/category/" + categoryDto.id());
        return ResponseEntity.created(location).body(categoryDto);
    }
}
