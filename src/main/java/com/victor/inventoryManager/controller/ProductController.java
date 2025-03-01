package com.victor.inventoryManager.controller;

import com.victor.inventoryManager.dto.*;
import com.victor.inventoryManager.exception.ProductNotFoundException;
import com.victor.inventoryManager.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductDto createProductDto) {

        ProductDto productDto = productService.createProduct(createProductDto);

        URI location = URI.create("/api/v1/product/" + productDto.id());

        return ResponseEntity.created(location).body(productDto);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ProductDto>> listAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        var pageResponse = productService.findAll(page, pageSize);

        return ResponseEntity.ok(new ApiResponse<>(
                pageResponse.getContent(),
                new PaginationResponse(
                        pageResponse.getNumber(),
                        pageResponse.getSize(),
                        pageResponse.getTotalElements(),
                        pageResponse.getTotalPages())
        ));
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable(value = "productId") Long productId) {
        try {
            ProductDto productDto = productService.findProductById(productId);
            return ResponseEntity.ok(productDto);
        } catch (ProductNotFoundException ex) {
            ErrorResponseDto errorResponse = new ErrorResponseDto("Produto não encontrado",
                    HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
