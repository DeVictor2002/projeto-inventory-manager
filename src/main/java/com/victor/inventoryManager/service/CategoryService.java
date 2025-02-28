package com.victor.inventoryManager.service;

import com.victor.inventoryManager.dto.CategoryDto;
import com.victor.inventoryManager.dto.CreateCategoryDto;
import com.victor.inventoryManager.entity.Category;
import com.victor.inventoryManager.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto createCategory(CreateCategoryDto createCategoryDto) {

        Category category = new Category();
        category.setName(createCategoryDto.name());

        Category categorySaved = categoryRepository.save(category);
        return new CategoryDto(categorySaved.getId(), categorySaved.getName());
    }
}
