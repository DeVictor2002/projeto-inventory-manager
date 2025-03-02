package com.victor.inventoryManager.controller;

import com.victor.inventoryManager.dto.*;
import com.victor.inventoryManager.exception.CategoryNotFoundException;
import com.victor.inventoryManager.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<ApiResponse<CategoryDto>> listAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        var pageResponse = categoryService.findAll(page, pageSize);

        return ResponseEntity.ok(new ApiResponse<>(
                pageResponse.getContent(),
                new PaginationResponse(
                        pageResponse.getNumber(),
                        pageResponse.getSize(),
                        pageResponse.getTotalElements(),
                        pageResponse.getTotalPages()
                )
        ));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoryById(@PathVariable(value = "categoryId") Long id) {
        try {
            CategoryDto categoryDto = categoryService.findById(id);
            return ResponseEntity.ok(categoryDto);
        } catch (CategoryNotFoundException ex) {
            ErrorResponseDto errorResponse = new ErrorResponseDto(ex.getMessage()
            , HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.noContent().build();
        } catch (CategoryNotFoundException ex) {
            ErrorResponseDto errorResponse = new ErrorResponseDto(ex.getMessage(),
                    HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
