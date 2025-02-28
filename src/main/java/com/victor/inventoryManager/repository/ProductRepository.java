package com.victor.inventoryManager.repository;

import com.victor.inventoryManager.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
