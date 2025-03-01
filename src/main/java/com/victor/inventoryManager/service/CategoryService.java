package com.victor.inventoryManager.service;

import com.victor.inventoryManager.dto.CategoryDto;
import com.victor.inventoryManager.dto.CreateCategoryDto;
import com.victor.inventoryManager.entity.Category;
import com.victor.inventoryManager.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<CategoryDto> findAll(Integer page, Integer pageSize) {

        var pageRequest = PageRequest.of(page, pageSize);

        Page<Category> categories = categoryRepository.findAll(pageRequest);

        return categories.map(category -> new CategoryDto(
                category.getId(),
                category.getName()
        ));
    }
}
