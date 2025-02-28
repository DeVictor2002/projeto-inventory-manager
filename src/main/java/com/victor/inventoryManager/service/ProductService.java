package com.victor.inventoryManager.service;

import com.victor.inventoryManager.entity.Product;
import com.victor.inventoryManager.repository.ProductRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRespository productRespository;

    public ProductService(ProductRespository productRespository) {
        this.productRespository = productRespository;
    }

    public Page<Product> findAll(Integer page, Integer pageSize) {

        var pageRequest = PageRequest.of(page, pageSize);

        return productRespository.findAll(pageRequest);
    }

}