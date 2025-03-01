package com.victor.inventoryManager.controller;

import com.victor.inventoryManager.dto.ApiResponse;
import com.victor.inventoryManager.dto.CategoryDto;
import com.victor.inventoryManager.dto.CreateCategoryDto;
import com.victor.inventoryManager.dto.PaginationResponse;
import com.victor.inventoryManager.service.CategoryService;
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
}
