package com.victor.inventoryManager.dto;

import java.math.BigDecimal;

public record UpdateProductDto(String name,
                               String description,
                               Integer quantity,
                               BigDecimal price,
                               Long categoryId) {
}
