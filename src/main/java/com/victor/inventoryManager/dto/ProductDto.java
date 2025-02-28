package com.victor.inventoryManager.dto;

import com.victor.inventoryManager.entity.Category;

import java.math.BigDecimal;

public record ProductDto(Long id,
                         String name,
                         String description,
                         Integer quantity,
                         BigDecimal price,
                         String category) {
}
