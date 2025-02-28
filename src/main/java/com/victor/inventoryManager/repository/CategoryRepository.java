package com.victor.inventoryManager.repository;

import com.victor.inventoryManager.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
