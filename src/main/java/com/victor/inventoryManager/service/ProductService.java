package com.victor.inventoryManager.service;

import com.victor.inventoryManager.dto.CreateProductDto;
import com.victor.inventoryManager.dto.ProductDto;
import com.victor.inventoryManager.dto.UpdateProductDto;
import com.victor.inventoryManager.entity.Category;
import com.victor.inventoryManager.entity.Product;
import com.victor.inventoryManager.exception.CategoryNotFoundException;
import com.victor.inventoryManager.exception.ProductNotFoundException;
import com.victor.inventoryManager.repository.CategoryRepository;
import com.victor.inventoryManager.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<ProductDto> findAll(Integer page, Integer pageSize) {

        var pageRequest = PageRequest.of(page, pageSize);

        Page<Product> products = productRepository.findAll(pageRequest);

        return products.map(product -> new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getCategory() != null ? product.getCategory().getName() : null
        ));
    }

    public ProductDto createProduct(CreateProductDto createProductDto) {

        Category category = categoryRepository.findById(createProductDto.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada"));

        Product product = new Product(
                createProductDto.name(),
                createProductDto.description(),
                createProductDto.quantity(),
                createProductDto.price(),
                category
        );

        Product productSaved = productRepository.save(product);

        return new ProductDto(productSaved.getId(),
                productSaved.getName(),
                productSaved.getDescription(),
                productSaved.getQuantity(),
                productSaved.getPrice(),
                productSaved.getCategory().getName());
    }

    public ProductDto findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getCategory() != null ? product.getCategory().getName() : null
        );
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));
        productRepository.delete(product);
    }

    public ProductDto updateProduct(Long productId, UpdateProductDto updateProductDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Produto não encontrado com o id: " + productId));

        Category category = null;
        if (updateProductDto.categoryId() != null) {
            category = categoryRepository.findById(updateProductDto.categoryId())
                    .orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada"));
        }

        product.setName(updateProductDto.name());
        product.setDescription(updateProductDto.description());
        product.setQuantity(updateProductDto.quantity());
        product.setPrice(updateProductDto.price());
        product.setCategory(category);

        Product productSaved = productRepository.save(product);

        return new ProductDto(
                productSaved.getId(),
                productSaved.getName(),
                productSaved.getDescription(),
                productSaved.getQuantity(),
                productSaved.getPrice(),
                productSaved.getCategory() != null ? productSaved.getCategory().getName() : null
        );
    }
}