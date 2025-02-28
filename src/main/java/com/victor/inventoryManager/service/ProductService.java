package com.victor.inventoryManager.service;

import com.victor.inventoryManager.dto.CreateProductDto;
import com.victor.inventoryManager.dto.ProductDto;
import com.victor.inventoryManager.entity.Product;
import com.victor.inventoryManager.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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

        Product product = new Product();
        product.setName(createProductDto.name());
        product.setDescription(createProductDto.description());
        product.setQuantity(createProductDto.quantity());
        product.setPrice(createProductDto.price());

        Product productSaved = productRepository.save(product);

        return new ProductDto(productSaved.getId(),
                productSaved.getName(),
                productSaved.getDescription(),
                productSaved.getQuantity(),
                productSaved.getPrice(),
                productSaved.getCategory().getName());
    }
}