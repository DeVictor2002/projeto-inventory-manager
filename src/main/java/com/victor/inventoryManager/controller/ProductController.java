package com.victor.inventoryManager.controller;

import com.victor.inventoryManager.controller.dto.ApiResponse;
import com.victor.inventoryManager.controller.dto.PaginationResponse;
import com.victor.inventoryManager.entity.Product;
import com.victor.inventoryManager.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Product>> listAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        var pageResponse = productService.findAll(page, pageSize);

        return ResponseEntity.ok(new ApiResponse<>(
                pageResponse.getContent(),
                new PaginationResponse(pageResponse.getNumber(),
                        pageResponse.getSize(),
                        pageResponse.getTotalElements(),
                        pageResponse.getTotalPages())
        ));
    }
}
